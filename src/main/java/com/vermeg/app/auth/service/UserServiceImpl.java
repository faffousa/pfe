package com.vermeg.app.auth.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.vermeg.app.auth.entity.Question;
import com.vermeg.app.auth.repo.QuestionRepository;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.domain.Sort;




import com.vermeg.app.auth.dto.LocalUser;
import com.vermeg.app.auth.dto.SignUpRequest;
import com.vermeg.app.auth.dto.SocialProvider;
import com.vermeg.app.auth.exception.OAuth2AuthenticationProcessingException;
import com.vermeg.app.auth.exception.UserAlreadyExistAuthenticationException;
import com.vermeg.app.auth.exception.UserNotFoundException;
import com.vermeg.app.auth.repo.RoleRepository;
import com.vermeg.app.auth.repo.UserRepository;
import com.vermeg.app.auth.repo.VerificationTokenRepository;
import com.vermeg.app.auth.security.oauth2.user.OAuth2UserInfo;
import com.vermeg.app.auth.security.oauth2.user.OAuth2UserInfoFactory;
import com.vermeg.app.auth.util.GeneralUtils;
import com.vermeg.app.auth.entity.Role;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.entity.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public VerificationToken sendVerificationToken(SignUpRequest signUpRequest) {
	      // Générer un jeton de vérification unique
	      String token = UUID.randomUUID().toString();
	      // Enregistrer le jeton de vérification dans la base de données
	      VerificationToken verificationToken = new VerificationToken(signUpRequest, token);
	      verificationToken.setId(UUID.randomUUID().toString());
	      verificationTokenRepository.save(verificationToken);
	      // Construire le message e-mail
	      //String subject = "CourZelo Mail Verification";
	      //String link = "http://localhost:8082/api/auth/verify-email/"+token ;
	      //String text = "<p>Hello,</p>"
		   //         + "<p>This process is to verify your Email.</p>"
		   //         + "<p>Click the link below to change your password:</p>"
		   //         + "<p><a href=\"" + link + "\">Verify my email</a></p>"
		   //         + "<br>"
		   //         + "<p>Ignore this email if you have not made the request. </p>";
		            
	      //SimpleMailMessage message = new SimpleMailMessage();
	      //message.setFrom("siwarzrelli180959@gmail.com");
	      //message.setTo(signUpRequest.getEmail());
	      //message.setSubject(subject);
	      //message.setText(text);
	      // Envoyer l'e-mail
	      //javaMailSender.send(message);
	      // Retourner le jeton de vérification
	      return verificationToken;
	   }
	
	@Override
	public VerificationToken getVerificationToken(String token) {
		return verificationTokenRepository.findByToken(token);
    }

	@Override
	@Transactional(value = "transactionManager")
	public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
		if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
			throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
		} else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
		}
		User user = buildUser(signUpRequest);
		Date now = Calendar.getInstance().getTime();
		user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		user.setCreatedDate(now);
		user.setModifiedDate(now);
		user = userRepository.save(user);
		//userRepository.flush();
		return user;
	}
	
	
	

	public User buildUser(final SignUpRequest formDTO) {
		
		User user = new User();
		
	//	user.setEmail(formDTO.getEmail());
	//	user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
		final HashSet<Role> roles = new HashSet<Role>();
		
		//Role Roles=formDTO.getRoles();
		//roles.add(Roles);
		
		//start create user
		user.setDisplayName(formDTO.getDisplayName());

		user.setEnabled(true);
		user.setProviderUserId(formDTO.getProviderUserId());


		Set<String> strRoles =  formDTO.getRoles();
		//Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(Role.USER);
				//	.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			user.setDisplayName(formDTO.getDisplayName());
				user.setEmail(formDTO.getEmail());
					user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
			roles.add(userRole);
		} else {

					Role adminRole = roleRepository.findByName(Role.ADMIN);
					user.setEmail(formDTO.getEmail());
					user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
						//	.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

			}



		
		
		//end code


		user.setRoles(roles);
	
		return user;
	}
	
	public boolean isEmailDomainAssociatedWithCompany(String email) {
	    String[] emailParts = email.split("@");
	    String domain = emailParts[1];
		boolean verif = true;
	    
	    // Liste de noms de domaines connus pour les fournisseurs de messagerie gratuits
	    String[] freeEmailProviders = {"gmail.com", "hotmail.com", "yahoo.com", "outlook.com", "aol.com", "protonmail.com", "mail.com", "zoho.com", "icloud.com", "yandex.com", "gmx.com", "tutanota.com", "mail.ru", "rediffmail.com", "inbox.com", "hushmail.com", "fastmail.com", "startmail.com", "runbox.com", "lavabit.com"};
	    
	    // Vérifier si le nom de domaine appartient à un fournisseur de messagerie gratuit
	    for (String provider : freeEmailProviders) {
	        if (domain.equalsIgnoreCase(provider)) {
	            verif = false;

	        }

	    }

	    return verif;
	   
	}


	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		User user = findUserByEmail(oAuth2UserInfo.getEmail());
		if (user != null) {
			if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(userDetails);
		}

		return LocalUser.create(user, attributes, idToken, userInfo);
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setDisplayName(oAuth2UserInfo.getName());
		return userRepository.save(existingUser);
	}

	private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
		return SignUpRequest.getBuilder().addProviderUserID(oAuth2UserInfo.getId()).addDisplayName(oAuth2UserInfo.getName()).addEmail(oAuth2UserInfo.getEmail())
				.addSocialProvider(GeneralUtils.toSocialProvider(registrationId)).addPassword("changeit").build();
	}

	@Override
	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}
	
	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any customer with the email " + email);
        }
    }
     
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }
     
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
         
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
    
    public List<User> findAllUsers(){
    	return userRepository.findAll();
    }

	@Override
	public List<User> findUsersByDisplayName(String displayName) {
		return userRepository.findByDisplayName(displayName);
	}


	@Override

	public  User getAuthenticatedUser() {
		String currName = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findOnByDisplayName(currName);
	}



	public User updateUser(User userDetails, long id) {

		User user = userRepository.findById(id).get();
		if (!userDetails.getDisplayName().isEmpty()){
			user.setDisplayName(userDetails.getDisplayName());
		}
		if (!userDetails.getEmail().isEmpty()){
			user.setEmail(userDetails.getEmail());
		}
		if (!userDetails.getPassword().isEmpty()){
			user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		}
		if (!userDetails.getRoles().isEmpty()){
			user.setRoles(userDetails.getRoles());
		}
		return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(long id) {
		
		User user = userRepository.findById(id).get();
		userRepository.delete(user);

	}

	@Override
	public boolean isEmailUnique(String email) {
		User user = userRepository.findByEmail(email);
		return user == null;
	}
	
	@Override
	public long addUser(SignUpRequest request) {
		
		User user = buildUser(request);
		Date now = Calendar.getInstance().getTime();
		user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		user.setCreatedDate(now);
		user.setModifiedDate(now);
		userRepository.save(user);
		// Send an email to the user
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getEmail());
		msg.setSubject("Welcome to Vermeg Plateform");
		msg.setText("Dear " + user.getDisplayName() + ",\n\nThank you for joining our application. Here are your login credentials:\nUsername: " + user.getEmail() + "\nPassword: " + request.getPassword() + "\n\nBest regards,\nThe team");

		javaMailSender.send(msg);
		return user.getId();
	}

	@Override
	public List<User> findUsersByEmail(String email){
		return userRepository.findUsersByEmail(email);
	}

	@Override
	public List<User> findUsersByRolesEquals(String roleName){
		Role newRole = new Role();
		if (roleName == "USER"){
			newRole = roleRepository.findByName(Role.USER);
		}
		if (roleName == "ADMIN"){
			newRole = roleRepository.findByName(Role.ADMIN);
		}

		return userRepository.findUsersByRoles(newRole);
	}

	@Override
	public void enableDisableUser (long id){
		User user = userRepository.findUserById(id);
		if (user.isEnabled()) {
			user.setEnabled(false);
		}else{
			user.setEnabled(true);
		}
		userRepository.save(user);

	}

	public void assignBadgeToUser(long userId, String badge) {
		User user = userRepository.findUserById(userId);
		user.setBadge(badge);
		userRepository.save(user);
	}



	public User getUserById(long idUser)  {
		User user = userRepository.findUserById(idUser);
		return  user ;

	}


	public List<User> getUserStatistics() {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.group("displayName")
						.sum("nbrRep").as("nbrRep"), // Utilisez sum() pour obtenir la somme réelle de nbrRep
				Aggregation.project(Fields.fields("_id", "nbrRep"))
						.and("displayName").previousOperation(),
				Aggregation.sort(Sort.by(Sort.Order.desc("nbrRep"))) // Utilisez Sort pour trier les résultats
		);

		AggregationResults<User> results = mongoTemplate.aggregate(aggregation, "user", User.class);

		return results.getMappedResults();
	}





}
