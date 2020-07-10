package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.model.User;
import com.bookstoreapp.repository.ICartRepository;
import com.bookstoreapp.repository.IUserDetailRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.IUserService;
import com.bookstoreapp.util.IJwtToken;
import com.bookstoreapp.util.IResetPasswordTemplate;
import com.bookstoreapp.util.ISendMail;
import com.bookstoreapp.util.IVerifyEmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IJwtToken jwtToken;

    @Autowired
    ICartRepository cartRepository;

    @Autowired
    IUserDetailRepository userDetailRepository;

    @Autowired
    ISendMail mailSender;

    @Autowired
    IVerifyEmailTemplate iVerifyEmailTemplate;

    @Autowired
    IResetPasswordTemplate iResetPasswordTemplate;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Response addUser(UserRegistrationDto userRegistrationDto, HttpServletRequest servletRequest) throws MessagingException {

        Optional<User> userdata = userRepository.findUserByEmail(userRegistrationDto.email);
        if (userdata.isPresent()) { throw new UserException("User Exists", UserException.ExceptionType.USER_ALREADY_EXIST); }

        userRegistrationDto.password = passwordEncoder.encode(userRegistrationDto.password);
        User savedUser = userRepository.save(new User(userRegistrationDto));
        String appUrl = iVerifyEmailTemplate.verifyEmailTemplate(servletRequest.getHeader("origin") +
                "/verify/account/?" + jwtToken.generateToken(savedUser.id));
        mailSender.sendMail(new NotificationDto(savedUser.email, "Activate account", appUrl));
        return new Response("User Registered Successfully", 200, "User Registered Successfully");
    }


    @Override
    public String loginUser(UserLoginDto userLoginDto) {

        Optional<User> userData = userRepository.findUserByEmail(userLoginDto.email);

        if (userData.isPresent()) {
            boolean booleanResult = passwordEncoder.matches(userLoginDto.password, userData.get().password);
            if (booleanResult) { return jwtToken.generateToken(userData.get().id); }
            throw new UserException("Incorrect password", UserException.ExceptionType.INVALID_PASSWORD);
        }
        throw new UserException("Invalid Email id", UserException.ExceptionType.INVALID_EMAIL_ID);
    }


    @Override
    public Response verifyEmail(String token) {

        Optional<User> savedUser = this.validateToken(token);
        savedUser.get().isActivate = true;
        userRepository.save(savedUser.get());
        return new Response("Account Is Activated", 200, "");
    }

    @Override
    public Response forgetPassword(String emailID, HttpServletRequest servletRequest) throws MessagingException {

        Optional<User> userdata = userRepository.findUserByEmail(emailID);
        if (userdata.isPresent()) {
            String appUrl =
                    servletRequest.getHeader("origin") + "/reset/password/?" + jwtToken.generateToken(userdata.get().id);
            String message = iResetPasswordTemplate.getPasswordTemplate(appUrl);
            mailSender.sendMail(new NotificationDto(userdata.get().email, "Reset Password", message));
            return new Response("Sent Email For Password Reset", 200, "User Fetched Successfully");
        }
        throw new UserException("No Such User", UserException.ExceptionType.USER_NOT_FOUND);
    }


    @Override
    public Response resetPassword(String token, String password) {

        Optional<User> user = this.validateToken(token);
        if (user.isPresent()) {
            if (user.get().isActivate) {
                user.get().password = passwordEncoder.encode(password);
                userRepository.save(user.get());
                return new Response("Password Reset Successfully", 200, "");
            }
            throw new UserException("User is not Activated Account", UserException.ExceptionType.USER_HAS_NOT_ACTIVATED_ACCOUNT);
        }
        throw new UserException("No Such User", UserException.ExceptionType.USER_NOT_FOUND);
    }

    private Optional<User> validateToken(String token) {
        jwtToken.validateToken(token);
        int userId = jwtToken.getUserId();
        return userRepository.findUserById(userId);
    }
}
