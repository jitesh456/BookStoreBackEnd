package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.model.User;
import com.bookstoreapp.model.UserDetail;
import com.bookstoreapp.repository.ICartRepository;
import com.bookstoreapp.repository.IUserDetailRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.IUserService;
import com.bookstoreapp.util.IJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public Response addUser(UserRegistrationDto userRegistrationDto) {
        Optional<User> userdata=userRepository.findUserByEmail(userRegistrationDto.email);
        if(!userdata.isPresent()) {
            String password = userRegistrationDto.password;
            String encodedPassowrd = passwordEncoder.encode(password);
            userRegistrationDto.password = encodedPassowrd;
            User user = new User(userRegistrationDto);
            userRepository.save(user);
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
                System.out.println("USER ID"+userData.get().id);
                return jwtToken.doGenerateToken(userData.get().id);
            }
            throw new UserException("Incorrect password",UserException.ExceptionType.INVALID_PASSWORD);
        }
        throw new UserException("Invalid Email id",UserException.ExceptionType.INVALID_EMAIL_ID);
    }

    @Override
    public Response userDetail(UserDetailDto userDetailsDto, String token) {
            jwtToken.validateToken(token);
            int userId=-1;
            userId= jwtToken.getUserId();
            if(userId!=-1){
                UserDetail userDetail=new UserDetail(userDetailsDto);
                userDetailRepository.save(userDetail);
                Optional<User> user=userRepository.findUserById(userId);
                userDetail.user=user.get();
                userDetailRepository.save(userDetail);
                user.get().userDetail.add(userDetail);
                userRepository.save(user.get());
                return new Response("Added User Detail Successfully",200,"");
            }
            throw new UserException("User Not Found", UserException.ExceptionType.USER_NOT_FOUND);
    }

    @Override
    public Response getUserDetail(String token) {
        return null;
    }
}
