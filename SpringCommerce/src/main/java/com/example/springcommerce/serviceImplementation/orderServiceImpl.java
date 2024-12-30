package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.orderBodyRequest;
import com.example.springcommerce.DTO.Request.orderRequest;
import com.example.springcommerce.entity.addressEntity;
import com.example.springcommerce.entity.cartEntity;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.addressRepo;
import com.example.springcommerce.repository.cartRepo;
import com.example.springcommerce.service.orderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class orderServiceImpl implements orderService{
    private final cartRepo cartRepository;
    private final addressRepo addressRepository;

    @Autowired
    public orderServiceImpl(cartRepo cartRepository, addressRepo addressRepository) {
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    @Transactional
    public orderRequest placeOrder(String emailId, String paymentMethod, orderBodyRequest orderRequestBody) {

        cartEntity cart = cartRepository.findCartByEmail(emailId);
        if(cart == null){
            throw new ResourceNotFound("Cart","email",emailId);
        }

        addressEntity address = addressRepository.findById(orderRequestBody.getAddressId()).orElseThrow(() -> new ResourceNotFound("Address","id",orderRequestBody.getAddressId()));

        return null;
    }
}
