package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.cartRequest;
import jakarta.transaction.Transactional;

import java.util.List;

public interface cartService {
    cartRequest addProductTocart(Long productId, Integer quantity);

    List<cartRequest> getAllCarts();

    cartRequest getCart(String email, Long cartId);

    @Transactional
    cartRequest updateProductQuantityInCart(Long productId, Integer delete);
}
