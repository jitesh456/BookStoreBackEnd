package com.bookstoreapp.service.Implementation;

import com.bookstoreapp.dto.AddToCartDto;
import com.bookstoreapp.response.Response;
import com.bookstoreapp.service.ICartService;
import org.springframework.stereotype.Service;

@Service
public class CartService  implements ICartService {

    @Override
    public Response addToCart(AddToCartDto addToCartDto, String token) {
        return null;
    }
}
