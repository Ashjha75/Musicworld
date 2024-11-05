package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.cartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springcommerce.service.cartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final cartService cartService;

    @Autowired
    public CartController(cartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/product/{productId}/quantity/{quantity}")
    public ResponseEntity<cartRequest> addProductToCart(@PathVariable Long productId, @PathVariable Integer quantity) {

        cartRequest cartRequest = cartService.addProductTocart(productId, quantity);

        return new ResponseEntity<>(cartRequest, HttpStatus.CREATED);
    }
}
