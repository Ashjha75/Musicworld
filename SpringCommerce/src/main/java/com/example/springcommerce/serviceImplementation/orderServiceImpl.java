package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.orderBodyRequest;
import com.example.springcommerce.DTO.Request.orderRequest;
import com.example.springcommerce.service.orderService;
import org.springframework.stereotype.Service;

@Service
public class orderServiceImpl implements orderService{


    @Override
    public orderRequest placeOrder(String emailId, String paymentMethod, orderBodyRequest orderRequestBody) {
        return null;
    }
}
