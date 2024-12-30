package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.orderBodyRequest;
import com.example.springcommerce.DTO.Request.orderRequest;
import com.example.springcommerce.entity.cartEntity;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.service.orderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class orderServiceImpl implements orderService{


    @Override
    @Transactional
    public orderRequest placeOrder(String emailId, String paymentMethod, orderBodyRequest orderRequestBody) {

        cartEntity cart = cartRepository.findByEmailId(emailId);
        if(cart == null){
            throw new ResourceNotFound("Cart","email",emailId);
        }

        return null;
    }
}
