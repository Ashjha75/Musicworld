package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.DTO.Response.productResponse;
import com.example.springcommerce.entity.productEntity;
import com.example.springcommerce.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class productController {

    private final productService productService;

    @Autowired
    public productController(productService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<productRequest> addProduct(@RequestBody productRequest productRequest, @PathVariable Long categoryId) {
        productRequest products = productService.addProduct(productRequest, categoryId);
        return new ResponseEntity<productRequest>(products, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<productResponse> getAllProducts() {
        productResponse productResponse = productService.getAllProducts();
        return new ResponseEntity<productResponse>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<productResponse> getProductByCategory(@PathVariable Long categoryId) {
        productResponse productResponse = productService.getProductByCategory(categoryId);
        return new ResponseEntity<productResponse>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyWord/{keyWord}")
    public ResponseEntity<productResponse> getProductByKeyWord(@PathVariable String keyWord) {
        productResponse productResponse = productService.getProductByKeyWord(keyWord);
        return new ResponseEntity<productResponse>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/productId/{productId}")
    public ResponseEntity<productRequest> updateProduct(@RequestBody productRequest productRequest, @PathVariable Long productId) {
        productRequest savedProduct = productService.updateProduct(productRequest, productId);
        return new ResponseEntity<productRequest>(savedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/productId/{productId}")
    public ResponseEntity<productRequest> deleteProduct(@PathVariable Long productId) {
        productRequest product = productService.deleteProduct(productId);
        return new ResponseEntity<productRequest>(product, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<productRequest> updateProductImage(@PathVariable Long productId, @RequestParam("image")MultipartFile image) {
        productRequest product = productService.updateProductImage(productId, image);
        return new ResponseEntity<productRequest>(product, HttpStatus.OK);
    }

}
