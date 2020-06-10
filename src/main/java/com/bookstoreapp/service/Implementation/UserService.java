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
import com.bookstoreapp.util.ISendMail;
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


    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public Response addUser(UserRegistrationDto userRegistrationDto, HttpServletRequest servletRequest) throws MessagingException{
        Optional<User> userdata=userRepository.findUserByEmail(userRegistrationDto.email);
        if(!userdata.isPresent()) {
            String password = userRegistrationDto.password;
            String encodedPassowrd = passwordEncoder.encode(password);
            userRegistrationDto.password = encodedPassowrd;
            User user = new User(userRegistrationDto);

            User savedUser=userRepository.save(user);

            String appUrl =
                    "http://" + servletRequest.getServerName() +
                            ":" + servletRequest.getServerPort()+"/verify?token="+jwtToken.generateToken(savedUser.id);
            NotificationDto notificationDto=new NotificationDto(savedUser.email,"Activate account",
                    appUrl);
            mailSender.sendMail(notificationDto);
            return new Response("User Registered Successfully",200,"User Registered Successfully");
        }

        throw new UserException("User Exists",UserException.ExceptionType.USER_ALREADY_EXIST);
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        Optional<User> userData = userRepository.findUserByEmail(userLoginDto.email);
        if(userData.isPresent()){

            boolean booleanResult = passwordEncoder.matches(userLoginDto.password, userData.get().password);
            if(booleanResult)
            {
                return jwtToken.generateToken(userData.get().id);
            }
            throw new UserException("Incorrect password",UserException.ExceptionType.INVALID_PASSWORD);
        }
        throw new UserException("Invalid Email id",UserException.ExceptionType.INVALID_EMAIL_ID);
    }

    @Override
    public Response verifyEmail(String token) {
        boolean result=jwtToken.validateToken(token);
        int userId = jwtToken.getUserId();
        Optional<User> savedUser = userRepository.findUserById(userId);
        savedUser.get().isActivate=true;
        userRepository.save(savedUser.get());
        return new Response("Account Is Activated",200,"");

    }

}
