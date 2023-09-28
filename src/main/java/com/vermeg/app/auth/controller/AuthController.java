package com.vermeg.app.auth.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.vermeg.app.auth.exception.UserAlreadyExistAuthenticationException;
import com.vermeg.app.auth.exception.UserNotFoundException;
import com.vermeg.app.auth.security.jwt.TokenProvider;
import com.vermeg.app.auth.service.UserService;
import com.vermeg.app.auth.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.vermeg.app.auth.dto.ApiResponse;
import com.vermeg.app.auth.dto.JwtAuthenticationResponse;
import com.vermeg.app.auth.dto.LocalUser;
import com.vermeg.app.auth.dto.LoginRequest;
import com.vermeg.app.auth.dto.SignUpRequest;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.entity.VerificationToken;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	TokenProvider tokenProvider;

	@Autowired
	JavaMailSender mailSender;

	@GetMapping("verify-email/{token}")
	public String verifyEmail(@PathVariable("token") String token) {
		// Vérifier si le jeton de vérification est valide et actif
		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			return "Le jeton de vérification est invalide ou expiré";
		}
		// Activer le compte utilisateur
		userService.registerNewUser(verificationToken.getSignUpRequest());
		// Rediriger l'utilisateur vers la page de connexion
		return "Votre adresse e-mail a été vérifiée avec succès. Veuillez vous connecter.";
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getEmail()+"aaa");
		String email = loginRequest.getEmail().replaceAll("\\s+","");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.createToken(authentication);

		LocalUser localUser = (LocalUser) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		VerificationToken verificationToken;
		try {
			// Envoyer un e-mail de vérification à l'utilisateur
			verificationToken = userService.sendVerificationToken(signUpRequest);
		} catch (UserAlreadyExistAuthenticationException e) {
			log.error("Exception Ocurred", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		// Retourner une réponse avec le jeton de vérification
		return ResponseEntity.ok(verificationToken);
	}

	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request) throws UserNotFoundException, UnsupportedEncodingException, MessagingException {
		String email = request.getParameter("email");
		String token = RandomString.make(30);

		userService.updateResetPasswordToken(token, email);
		String resetPasswordLink = "http://localhost:4200/reset?token=" + token;
		sendEmail(email, resetPasswordLink);
		System.out.println(resetPasswordLink);
		return token;
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") long id) {
		Optional<User> user = userService.findUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	public void sendEmail(String recipientEmail, String link)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("siwarzrelli180959@gmail.com", "Courzelo Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>"
				+ "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>"
				+ "<p><a href=\"" + link + "\">Change my password</a></p>"
				+ "<br>"
				+ "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);


		helper.setText(content, true);

		mailSender.send(message);
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		User user = userService.getByResetPasswordToken(token);
		userService.updatePassword(user, password);
		return "message";
	}

	@GetMapping("/get_user")
	public User getAuthenticatedUser() {
		return userService.getAuthenticatedUser();
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllEmployees() {
		List<User> users = userService.findAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PostMapping("/validateToken/{token}")
	public ResponseEntity<Boolean> verifyToken(@PathVariable String token) {
		System.out.println(token);
		return new ResponseEntity<>(tokenProvider.validateToken(token), HttpStatus.OK);
	}

	@GetMapping("/search/{displayName}")
	public ResponseEntity<List<User>> getUserByDisplayName(@PathVariable("displayName") String displayName) {
		List<User> users = userService.findUsersByDisplayName(displayName);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateEmployee(@RequestBody User userDetails, @PathVariable long id) {
		User updateUser = userService.updateUser(userDetails, id);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);

	}
}
