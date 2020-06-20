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

        Optional<User> savedUser = validate(token);
        User user=savedUser.get();
        if(!savedUser.isPresent()){ throw new UserException("User Not Found", UserException.ExceptionType.USER_NOT_FOUND); }
        Optional<UserDetail> detail=userDetailRepository.findAll().stream()
                .filter(details -> userDetailsDto.addressType.equals(details.addressType))
                .findFirst();
        if(detail.isPresent()){
            user.userDetail.removeIf(details->detail.get().addressType.equals(details.addressType));
            userDetailRepository.delete(detail.get());
        }
        UserDetail userDetail=new UserDetail(userDetailsDto);
        userDetailRepository.save(userDetail);
        user.userDetail.add(userDetail);
        userRepository.save(user);
        return new Response("Added User Detail Successfully",200,"");
    }

    @Override
    public Response getUserDetail(String token) {

        Optional<User> savedUser=validate(token);

        if(savedUser.isPresent()){

            return new Response("User Found",200,savedUser.get());
        }
        throw new UserException("User Not Found", UserException.ExceptionType.USER_NOT_FOUND);
    }

    private Optional<User> validate(String token){

        jwtToken.validateToken(token);
        int userId = jwtToken.getUserId();
        return userRepository.findUserById(userId);
    }
}