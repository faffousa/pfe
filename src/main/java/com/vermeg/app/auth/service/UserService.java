package com.vermeg.app.auth.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vermeg.app.auth.dto.LocalUser;
import com.vermeg.app.auth.dto.SignUpRequest;
import com.vermeg.app.auth.exception.UserAlreadyExistAuthenticationException;
import com.vermeg.app.auth.exception.UserNotFoundException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.entity.VerificationToken;


public interface UserService {
	
	public VerificationToken sendVerificationToken(SignUpRequest signUpRequest);
	public VerificationToken getVerificationToken(String token);

	public boolean isEmailUnique(String email);
	public List<User> findUsersByEmail(String email);
	public List<User> findUsersByRolesEquals(String roleName);
	public void enableDisableUser (long id);
	public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	User findUserByEmail(String email);

	Optional<User> findUserById(Long id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
	
	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException;
	
	public User getByResetPasswordToken(String token);
	
	public void updatePassword(User user, String newPassword);
	
	public List<User> findAllUsers();

	public List<User> findUsersByDisplayName(String displayName);
	public  User getAuthenticatedUser();
	public User updateUser(User userDetails, long id);
	
	public void deleteUser(long id);
	public long addUser(SignUpRequest request);
	public void assignBadgeToUser(long userId, String badge);

	public User getUserById(long idUser);
	public List<User> getUserStatistics();




}
