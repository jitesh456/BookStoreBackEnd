package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.util.IJwtToken;
import com.bookstoreapp.util.implementation.JwtToken;
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
    IJwtToken iJwtToken;
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
    public String loginUser(UserLoginDto userLoginDto) {
        Optional<User> userData = userRepository.findUserByEmail(userLoginDto.email);
        if(userData.isPresent()){

            boolean booleanResult = passwordEncoder.matches(userLoginDto.password, userData.get().password);
            if(booleanResult)
            {
                return iJwtToken.doGenerateToken(userData.get().id);
            }
            throw new UserException("Incorrect password",UserException.ExceptionType.INVALID_PASSWORD);
        }
        throw new UserException("Invalid Email id",UserException.ExceptionType.INVALID_EMAIL_ID);
    }

}
