package com.vermeg.app.auth.controller;

import com.vermeg.app.auth.dto.LocalUser;
import com.vermeg.app.auth.dto.SignUpRequest;
import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.service.UserService;
import com.vermeg.app.auth.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.vermeg.app.auth.config.CurrentUser;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/user/me")
	public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
		return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}
	@GetMapping("/all")
	public ResponseEntity<?> getContent() {
		return ResponseEntity.ok("Public content goes here");
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_TRAINEE')")
	public ResponseEntity<?> getUserContent() {
		return ResponseEntity.ok("User content goes here");
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAdminContent() {
		return ResponseEntity.ok("Admin content goes here");
	}



	@PostMapping("/addUser")
//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addUser(@RequestBody SignUpRequest request) {
		String email = request.getEmail();
		if (userService.isEmailUnique(email)) {
			long userId = userService.addUser(request);
			return ResponseEntity.ok("User added successfully with id " + userId);
		} else {
			return ResponseEntity.badRequest().body("User with email " + email + " already exists");
		}
	}


	@DeleteMapping("/delete/{id}")
	//@PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")
	public ResponseEntity deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/checkMail/{email}")
	public boolean isEmailUnique(@PathVariable("email") String email){
		return userService.isEmailUnique(email);
	}

	@GetMapping("/findByEmail/{email}")
	public List<User> findUsersByEmail(@PathVariable("email") String email){
		return userService.findUsersByEmail(email);
	}

	@GetMapping("/getUserById/{idUser}")
	public ResponseEntity<User> getUserById(@PathVariable("idUser") long idUser) {
		User user = userService.getUserById(idUser);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}





	@GetMapping("/findByRole/{roleName}")
	public List<User> findUsersByRole(@PathVariable("roleName") String roleName){
		return userService.findUsersByRolesEquals(roleName);
	}

	@PutMapping("/enableDisable/{id}")
	public ResponseEntity enableDisableUser (@PathVariable("id") long id){
		userService.enableDisableUser(id);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping("/{userId}/badge")
	public void assignBadgeToUser(@PathVariable Long userId, @RequestBody String badge) {
		userService.assignBadgeToUser(userId, badge);
	}


	@GetMapping("/user/statistics")
	public ResponseEntity<List<User>> getUserStatistics() {
		List<User> userStatistics = userService.getUserStatistics();
		return ResponseEntity.ok(userStatistics);
	}
	
}
