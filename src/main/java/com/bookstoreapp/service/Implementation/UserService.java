package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.enums.LoginResponseMessage;
import com.bookstoreapp.exception.UserException;
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

    @Override
    public boolean addUser(UserRegistrationDto userRegistrationDto) {
        Optional<User> userdata=userRepository.findUserByEmail(userRegistrationDto.email);
        if(!userdata.isPresent()) {
            String password = userRegistrationDto.password;
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
   Optional<User> userdata=userRepository.findUserByEmail(userLoginDto.email);
        if(userdata.isPresent()){
            BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
            boolean booleanResult = passwordEncoder.matches(userLoginDto.password, userdata.get().password);
            LoginResponseMessage.RESPONSE_MESSAGE.responseMessage(booleanResult);

            return new Response(LoginResponseMessage.RESPONSE_MESSAGE.responseMessage(booleanResult), 200,booleanResult);
        }
        throw new UserException("Invalid User Id or password",UserException.ExceptionType.INVALID_EMAIL_ID);
    }
}
