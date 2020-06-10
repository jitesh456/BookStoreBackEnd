package com.bookstoreapp.service.Implementation;


import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.model.User;
import com.bookstoreapp.model.UserDetail;
import com.bookstoreapp.repository.IUserDetailRepository;
import com.bookstoreapp.repository.IUserRepository;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.ICustomerService;
import com.bookstoreapp.util.IJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IJwtToken jwtToken;

    @Autowired
    IUserDetailRepository userDetailRepository;


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
        jwtToken.validateToken(token);
        int userId=-1;
        if(userId!=-1){
            userId = jwtToken.getUserId();
            Optional<User> user=userRepository.findUserById(userId);
            List<UserDetail> userDetail=new ArrayList<>();
            for(int i=0;i<user.get().userDetail.size();i++){
                userDetail.add(user.get().userDetail.get(i));
            }
            return new Response("User Found",200,userDetail);
        }
        throw new UserException("User Not Found", UserException.ExceptionType.USER_NOT_FOUND);
    }


}
