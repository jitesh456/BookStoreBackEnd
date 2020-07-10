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

    @Autowired
    private  IFeedbackRepository feedbackRepository;

    @Autowired
    IUserDetailRepository userDetailRepository;

    @Override
    public Response userDetail(UserDetailDto userDetailsDto, String token) {

        User user = validate(token).get();
        if (user != null) { throw new UserException("User Not Found", UserException.ExceptionType.USER_NOT_FOUND); }
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
        if (savedUser.isPresent()) { return new Response("User Found", 200, savedUser.get()); }
        throw new UserException("User Not Found", UserException.ExceptionType.USER_NOT_FOUND);
    }

    @Override
    public Response addFeedback(String token, FeedbackDto feedbackDto) {

        if(token != null) {
            User user = validate(token).get();
            Optional<Book> book1 = bookRepository.findByIsbn(feedbackDto.isbn);
            Book book = book1.get();
            List<Integer> feedbackIds = feedbackRepository.getfeedbackIds(book1.get().id);
            if (feedbackIds.size()==0) {
                feedbackRepository.save(new Feedback(user.id, feedbackDto.rating, feedbackDto.feedbackMessage,book));
                return new Response("Thank you For your Feedback ", 200, "Feedback added Successfully");
            }
            throw new UserException("You had submitted feedback previously", UserException.ExceptionType.YOU_HAD_SUBMITTED_FEEDBACK_PREVIOUSLY);
        }
        throw new UserException("Please Login to give feedback", UserException.ExceptionType.PLEASE_LOGIN_TO_GIVE_FEEDBACK);
   }

    @Override
    public Response getAllFeedback(String isbn) {

        List<Integer> feedbackIds = feedbackRepository.getfeedbackIds(bookRepository.findByIsbn(isbn).get().id);
        List<FeedbackResponse> feedbackList= new ArrayList<>();

        for(int i=0;i<feedbackIds.size();i++) {
            Optional<Feedback> feedback = feedbackRepository.findById(feedbackIds.get(i));
            Optional<User> userById = userRepository.findUserById(feedback.get().userId);
            feedbackList.add(new FeedbackResponse(feedback.get().rating,feedback.get().feedbackMessage,userById.get().name));
        }
        return new Response("Fetched All Feedbacks",200,feedbackList);
    }

    @Override
    public Response getUserFeedback(int bookId, String token) {
        Optional<User> user = validate(token);
        List<FeedbackResponse> feedbackList= new ArrayList<>();
        if (user.isPresent()){
            Integer feedbackId= feedbackRepository.getFeedbackId(user.get().id, bookId);
            if(feedbackId == null){
                throw new UserException("Feedback not found for this user", UserException.ExceptionType.FEEDBACK_NOT_FOUND); }
            Optional<Feedback> feedback = feedbackRepository.findById(feedbackId);
            feedbackList.add(new FeedbackResponse(feedback.get().rating, feedback.get().feedbackMessage, user.get().name));
            return new Response("User Feedback Fetched", 200, feedbackList);

        }
        throw new UserException("User not Found", UserException.ExceptionType.USER_NOT_FOUND);
    }


    private Optional<User> validate(String token) {

        jwtToken.validateToken(token);
        int userId = jwtToken.getUserId();
        return userRepository.findUserById(userId);
    }
}
