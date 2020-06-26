package com.bookstoreapp.service.Implementation;


import com.bookstoreapp.dto.FeedbackDto;
import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.model.*;
import com.bookstoreapp.repository.*;
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
    private IBookRepository bookRepository;

    @Autowired
    IJwtToken jwtToken;

    @Autowired
    private  IBookFeedbackRepository bookFeedbackRepository;

    @Autowired
    private  IFeedbackRepository feedbackRepository;

    @Autowired
    IUserDetailRepository userDetailRepository;

    @Override
    public Response userDetail(UserDetailDto userDetailsDto, String token) {

        Optional<User> savedUser = validate(token);
        User user = savedUser.get();
        if (!savedUser.isPresent()) {
            throw new UserException("User Not Found", UserException.ExceptionType.USER_NOT_FOUND);
        }
        Optional<UserDetail> detail = userDetailRepository.findAll().stream()
                .filter(details -> userDetailsDto.addressType.equals(details.addressType))
                .findFirst();
        if (detail.isPresent()) {
            user.userDetail.removeIf(details -> detail.get().addressType.equals(details.addressType));
            userDetailRepository.delete(detail.get());
        }
        UserDetail userDetail = new UserDetail(userDetailsDto);
        userDetailRepository.save(userDetail);
        user.userDetail.add(userDetail);
        userRepository.save(user);
        return new Response("Added User Detail Successfully", 200, "");
    }

    @Override
    public Response getUserDetail(String token) {

        Optional<User> savedUser = validate(token);

        if (savedUser.isPresent()) {

            return new Response("User Found", 200, savedUser.get());
        }
        throw new UserException("User Not Found", UserException.ExceptionType.USER_NOT_FOUND);
    }

    @Override
    public Response addFeedback(String token, FeedbackDto feedbackDto) {

        Feedback userFeedback = null;
        if(token != null) {
            User user = validate(token).get();
            String userName = user.name;
            String isbn = feedbackDto.isbn;
            Optional<Book> book1 = bookRepository.findByIsbn(isbn);
            Book book = book1.get();
            String feedbackMessage = feedbackDto.feedbackMessage;
            int rating = feedbackDto.rating;
            userFeedback = new Feedback(userName, rating, feedbackMessage);
            feedbackRepository.save(userFeedback);
            BookFeedback bookFeedback = new BookFeedback(book, userFeedback);
            BookFeedback bookFeedback1 = bookFeedbackRepository.save(bookFeedback);
            return new Response("Thank you For your Feedback ", 200, "Feedback added Successfully");
        }
        throw new UserException("Please Login to give feedback", UserException.ExceptionType.Please_Login_To_Give_Feedback);
   }


    private Optional<User> validate(String token) {

        jwtToken.validateToken(token);
        int userId = jwtToken.getUserId();
        return userRepository.findUserById(userId);
    }
}