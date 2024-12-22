package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.cartRequest;

import java.util.List;

public interface cartService {
    cartRequest addProductTocart(Long productId, Integer quantity);

    List<cartRequest> getAllCarts();

    cartRequest getCart(String email, Long cartId);
}
