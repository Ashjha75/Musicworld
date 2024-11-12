package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.cartRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cart API", description = "Endpoints for managing the cart")
public class CartController {

    private final cartService cartService;

    @Autowired
    public CartController(cartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/product/{productId}/quantity/{quantity}")
    @Operation(summary = "Add product to cart", description = "Add a product to the cart with the specified quantity")
    public ResponseEntity<cartRequest> addProductToCart(@PathVariable Long productId, @PathVariable Integer quantity) {

        cartRequest cartRequest = cartService.addProductTocart(productId, quantity);

        return new ResponseEntity<>(cartRequest, HttpStatus.CREATED);
    }
}
