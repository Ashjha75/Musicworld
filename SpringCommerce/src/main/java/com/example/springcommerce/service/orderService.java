package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.orderBodyRequest;
import com.example.springcommerce.DTO.Request.orderRequest;
import jakarta.transaction.Transactional;

public interface orderService {
    @Transactional
    orderRequest placeOrder(String emailId, String paymentMethod, orderBodyRequest orderRequestBody);
}
