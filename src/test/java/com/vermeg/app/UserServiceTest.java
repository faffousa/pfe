package com.vermeg.app;

import com.vermeg.app.auth.dto.SignUpRequest;
import com.vermeg.app.auth.dto.SocialProvider;
import com.vermeg.app.auth.exception.UserAlreadyExistAuthenticationException;
import com.vermeg.app.auth.repo.RoleRepository;
import com.vermeg.app.auth.repo.UserRepository;
import com.vermeg.app.auth.repo.VerificationTokenRepository;
import com.vermeg.app.auth.entity.Role;
import com.vermeg.app.auth.entity.User;
import com.vermeg.app.auth.service.SequenceGeneratorService;
import com.vermeg.app.auth.service.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = VermegApplication.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private VerificationTokenRepository verificationTokenRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private JavaMailSender javaMailSender;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    private SignUpRequest signUpRequestDto;
    @BeforeEach
    public void setup() {
        //initialisation test Signup Request
        signUpRequestDto = new SignUpRequest();
        signUpRequestDto.setUserID(1L);
        signUpRequestDto.setEmail("test@gmail.com");
        signUpRequestDto.setPassword("test123");
        signUpRequestDto.setMatchingPassword("test123");
        signUpRequestDto.setRoles(new HashSet<>(Arrays.asList("admin")));
        signUpRequestDto.setDisplayName("test");
        signUpRequestDto.setProviderUserId("testId");
    }

    @Test
    public void testRegisterNewUser_WithExistingUserId_ThrowsUserAlreadyExistAuthenticationException() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserID(1L);
        when(userRepository.existsById(signUpRequest.getUserID())).thenReturn(true);

        // Act + Assert
        assertThrows(UserAlreadyExistAuthenticationException.class, () -> {
            userService.registerNewUser(signUpRequest);
        });
    }

    @Test
    public void testRegisterNewUser_WithExistingEmail_ThrowsUserAlreadyExistAuthenticationException() {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("test@gmail.com");
        when(userRepository.existsByEmail(signUpRequest.getEmail())).thenReturn(true);

        // Act + Assert
        assertThrows(UserAlreadyExistAuthenticationException.class, () -> {
            userService.registerNewUser(signUpRequest);
        });
    }

    @Test
    public void testRegisterNewUser_WithNoRoles_SetsTraineeRole() throws UserAlreadyExistAuthenticationException {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserID(30L);
        signUpRequest.setEmail("test@yahoo.com");
        signUpRequest.setPassword("password");
        signUpRequest.setMatchingPassword("password");
        signUpRequest.setDisplayName("Test User");
        signUpRequest.setProviderUserId("test2Id");
        when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("encodedPassword");
        when(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME)).thenReturn(124L);
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return user;
        });

        // Act
        User user = userService.registerNewUser(signUpRequest);

        // Assert
        assertThat(user.getEmail()).isEqualTo(signUpRequest.getEmail());
        assertThat(user.getPassword()).isEqualTo("encodedPassword");
        assertThat(user.getDisplayName()).isEqualTo(signUpRequest.getDisplayName());
        assertThat(user.getRoles().size()).isEqualTo(1);
        assertThat(user.getRoles().contains(Role.USER));
    }

    @Test
    public void testRegisterNewUser_WithAdminRole_SetsAdminRole() throws UserAlreadyExistAuthenticationException {
        // Arrange
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserID(80L);
        signUpRequest.setEmail("test@example.com");
        signUpRequest.setPassword("password");
        signUpRequest.setMatchingPassword("password");
        signUpRequest.setDisplayName("test2");
        signUpRequest.setProviderUserId("test1Id");
        signUpRequest.setRoles(new HashSet<>(Arrays.asList("admin")));

        when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("encodedPassword");
        when(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME)).thenReturn(123L);
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return user;
        });

        // Act
        User user = userService.registerNewUser(signUpRequest);

        // Assert
        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
        assertThat(user.getRoles().contains(Role.ADMIN));
        assertEquals(Long.valueOf(123), user.getId());
    }
}


