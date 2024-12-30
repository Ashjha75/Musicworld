package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.orderBodyRequest;
import com.example.springcommerce.DTO.Request.orderRequest;

public interface orderService {
    orderRequest placeOrder(String emailId, String paymentMethod, orderBodyRequest orderRequestBody);
}
