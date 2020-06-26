package com.bookstoreapp.service;

import com.bookstoreapp.dto.FeedbackDto;
import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.model.Feedback;
import com.bookstoreapp.response.Response;


public interface ICustomerService {

    Response userDetail(UserDetailDto userDetailsDto, String token);

    Response getUserDetail(String token);

    Response addFeedback(String token, FeedbackDto feedbackDto);

    Iterable<Feedback> getAllFeedback(String token);
}
