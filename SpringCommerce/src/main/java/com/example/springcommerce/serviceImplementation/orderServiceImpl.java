package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.orderBodyRequest;
import com.example.springcommerce.DTO.Request.orderRequest;
import com.example.springcommerce.entity.addressEntity;
import com.example.springcommerce.entity.cartEntity;
import com.example.springcommerce.entity.orderEntity;
import com.example.springcommerce.entity.paymentEntity;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.*;
import com.example.springcommerce.service.orderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class orderServiceImpl implements orderService {
    private final cartRepo cartRepository;
    private final addressRepo addressRepository;
    private final orderItemRepo orderItemRepository;
    private final orderRepo orderRepository;
    private final paymentRepo paymentRepository;

    @Autowired
    public orderServiceImpl(cartRepo cartRepository, addressRepo addressRepository, orderItemRepo orderItemRepository, orderRepo orderRepository) {
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }


    @Override
    @Transactional
    public orderRequest placeOrder(String emailId, String paymentMethod, orderBodyRequest orderRequestBody) {

        cartEntity cart = cartRepository.findCartByEmail(emailId);
        if (cart == null) {
            throw new ResourceNotFound("Cart", "email", emailId);
        }

        addressEntity address = addressRepository.findById(orderRequestBody.getAddressId()).orElseThrow(() -> new ResourceNotFound("Address", "id", orderRequestBody.getAddressId()));

        orderEntity order = new orderEntity();
        order.setEmail(emailId);
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Order Accepted");
        order.setAddress(address);

        paymentEntity payment = new paymentEntity(paymentMethod, orderRequestBody.getPaymentGatewayPaymentId(),orderRequestBody.getPaymentGatewayPaymentStatus(),orderRequestBody.getPaymentGatewayResponseMessage(),


        return null;
    }
}
