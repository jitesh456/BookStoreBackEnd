package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.UserRegistrationDto;
import com.bookstoreapp.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Override
    public boolean addUser(UserRegistrationDto userRegistrationDto) {
        return false;
    }
}
