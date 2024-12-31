package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.orderBodyRequest;
import com.example.springcommerce.DTO.Request.orderRequest;
import com.example.springcommerce.service.orderService;
import com.example.springcommerce.utils.utilityGroup.AuthUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order API", description = "Endpoints for managing orders")
public class OrderController {

    private final AuthUtil authUtil;
    private final orderService orderService;

    @Autowired
    public OrderController(AuthUtil authUtil, orderService orderService) {
        this.authUtil = authUtil;
        this.orderService = orderService;
    }

    @PostMapping("/users/payments/{paymentMethod}")
    public ResponseEntity<orderRequest> orderProducts(@PathVariable String paymentMethod, @RequestBody orderBodyRequest orderRequestBody) {
        String emailId = authUtil.loggedInEmail();
        orderRequest order = orderService.placeOrder(emailId, paymentMethod, orderRequestBody);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


}
