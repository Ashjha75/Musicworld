package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.cartRequest;

public interface cartService {
    cartRequest addProductTocart(Long productId, Integer quantity);
}
