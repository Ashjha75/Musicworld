package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.cartRequest;
import com.example.springcommerce.entity.cartEntity;
import com.example.springcommerce.repository.cartRepo;
import com.example.springcommerce.service.cartService;
import com.example.springcommerce.utils.utilityGroup.AuthUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart API", description = "Endpoints for managing the cart")
public class CartController {

    private final cartService cartService;
    private final AuthUtil authUtil;
    private final com.example.springcommerce.repository.cartRepo cartRepo;

    @Autowired
    public CartController(cartService cartService, AuthUtil authUtil, cartRepo cartRepo) {
        this.cartService = cartService;
        this.authUtil = authUtil;
        this.cartRepo = cartRepo;
    }

    @PostMapping("/product/{productId}/quantity/{quantity}")
    @Operation(summary = "Add product to cart", description = "Add a product to the cart with the specified quantity")
    public ResponseEntity<cartRequest> addProductToCart(@PathVariable Long productId, @PathVariable Integer quantity) {

        cartRequest cartRequest = cartService.addProductTocart(productId, quantity);

        return new ResponseEntity<cartRequest>(cartRequest, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-carts")
    @Operation(summary = "Get all carts", description = "Get all carts")
    public ResponseEntity<List<cartRequest>> getAllCarts() {

        List<cartRequest> cartRequest = cartService.getAllCarts();

        return new ResponseEntity<List<cartRequest>>(cartRequest, HttpStatus.FOUND);
    }

    @GetMapping("/get-cart")
    @Operation(summary = "Get cart by ID", description = "Get cart by ID")
    public ResponseEntity<cartRequest> getCartById() {
        String email = authUtil.loggedInEmail();
        cartEntity cart = cartRepo.findCartByEmail(email);
        Long cartId = cart.getCartId();
        cartRequest cartRequest = cartService.getCart(email, cartId);

        return new ResponseEntity<cartRequest>(cartRequest, HttpStatus.FOUND);
    }

}
