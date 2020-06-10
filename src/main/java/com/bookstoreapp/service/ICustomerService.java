package com.bookstoreapp.service;

import com.bookstoreapp.dto.UserDetailDto;
import com.bookstoreapp.response.Response;

public interface ICustomerService {

    Response userDetail(UserDetailDto userDetailsDto, String token);

    Response getUserDetail(String token);
}
