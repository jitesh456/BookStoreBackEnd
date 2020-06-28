package com.bookstoreapp.service.Implementation;


import com.bookstoreapp.dto.FeedbackDto;
import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.exception.UserException;
import com.bookstoreapp.model.*;
import com.bookstoreapp.repository.*;
import com.bookstoreapp.response.FeedbackResponse;
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
    private IBookRepository bookRepository;

    @Autowired
    IJwtToken jwtToken;

//    @Autowired
//    private  IBookFeedbackRepository bookFeedbackRepository;

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
        boolean isUserFeedbackPresent= false;
        Feedback userFeedback = null;
        if(token != null) {
            User user = validate(token).get();
            int userId = user.id;
            String isbn = feedbackDto.isbn;
            Optional<Book> book1 = bookRepository.findByIsbn(isbn);
            Book book = book1.get();
            int bookid = book.id;
            List<Integer> feedbackIds = feedbackRepository.getfeedbackIds(bookid);
            if(feedbackIds.size()>0) {
                for (int i = 0; i < feedbackIds.size(); i++) {
                    int userFeedbackId = feedbackRepository.getUserFeedbackId(feedbackIds.get(i));
                    if (userId == userFeedbackId) {
                        isUserFeedbackPresent = true;
                        break;
                    }
                }
            }
            if (!isUserFeedbackPresent) {
                String feedbackMessage = feedbackDto.feedbackMessage;
                int rating = feedbackDto.rating;
                userFeedback = new Feedback(userId, rating, feedbackMessage,book);
                feedbackRepository.save(userFeedback);
                return new Response("Thank you For your Feedback ", 200, "Feedback added Successfully");
            }
            else
                throw new UserException("You had submitted feedback previously", UserException.ExceptionType.You_Had_Submitted_Feedback_Previously);
        }
        throw new UserException("Please Login to give feedback", UserException.ExceptionType.Please_Login_To_Give_Feedback);
   }

    @Override
    public Response getAllFeedback(String isbn) {

        Optional<Book> book = bookRepository.findByIsbn(isbn);
        Book bookdData= book.get();
        Integer id = bookdData.id;
        List<Integer> feedbackIds = feedbackRepository.getfeedbackIds(id);
        List<FeedbackResponse> feedbackList= new ArrayList<>();

        for(int i=0;i<feedbackIds.size();i++) {
            Optional<Feedback> feedback = feedbackRepository.findById(feedbackIds.get(i));
            Feedback feedback1 = feedback.get();
            int userId = feedback1.userId;
            Optional<User> userById = userRepository.findUserById(userId);
            String name = userById.get().name;
            feedbackList.add(new FeedbackResponse(feedback1.rating,feedback1.feedbackMessage,name));

        }
        return new Response("Fetched All Feedbacks",200,feedbackList);
    }


    private Optional<User> validate(String token) {

        jwtToken.validateToken(token);
        int userId = jwtToken.getUserId();
        return userRepository.findUserById(userId);
    }
}



