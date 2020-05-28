package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.UserLoginDto;
import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.model.User;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    IUserRepository userRepository;

    @Override
    public boolean addUser(UserRegistrationDto userRegistrationDto) {
        String password=userRegistrationDto.password;
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encodedPassowrd=passwordEncoder.encode(password);
        userRegistrationDto.password=encodedPassowrd;
        User user=new User(userRegistrationDto);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean loginUser(UserLoginDto userLoginDto) {
        return false;
    }
}
