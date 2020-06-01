package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.jwt.authentication.JwtAuthentication;
import com.bookstoreapp.model.User;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    JwtAuthentication jwtAuthentication;
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public boolean addUser(UserRegistrationDto userRegistrationDto) {
        Optional<User> userdata=userRepository.findUserByEmail(userRegistrationDto.email);
        if(!userdata.isPresent()) {
            String password = userRegistrationDto.password;
            String encodedPassowrd = passwordEncoder.encode(password);
            userRegistrationDto.password = encodedPassowrd;
            User user = new User(userRegistrationDto);
            userRepository.save(user);
            return true;
        }
        throw new UserException("User Exists",UserException.ExceptionType.USER_ALREADY_EXIST);
    }

    @Override
    public Response loginUser(UserLoginDto userLoginDto) {
        Optional<User> userData = userRepository.findUserByEmail(userLoginDto.email);
        if(userData.isPresent()){

            boolean booleanResult = passwordEncoder.matches(userLoginDto.password, userData.get().password);
            if(booleanResult)
            {
                return new Response("Login Successful",
                    200,jwtAuthentication.generateToken(userData.get()));
            }
            return new Response("Login failed", 200,"");
        }
        throw new UserException("Invalid User Id or password",UserException.ExceptionType.INVALID_EMAIL_ID);
    }

}
