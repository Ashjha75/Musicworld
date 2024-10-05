package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.DTO.Response.productResponse;
import com.example.springcommerce.entity.productEntity;
import com.example.springcommerce.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class productController {

    private final productService productService;

    @Autowired
    public productController(productService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<productRequest> addProduct(@RequestBody productEntity productEntity, @PathVariable Long categoryId) {
        productRequest products = productService.addProduct(productEntity, categoryId);
        return new ResponseEntity<productRequest>(products, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<productResponse> getAllProducts(){
        productResponse productResponse= productService.getAllProducts();
        return new ResponseEntity<productResponse>(productResponse,HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<productResponse> getProductByCategory(@PathVariable Long categoryId){
        productResponse productResponse= productService.getProductByCategory(categoryId);
        return new ResponseEntity<productResponse>(productResponse,HttpStatus.OK);
    }

    public ResponseEntity<productRequest> getProductByKeyWord(@PathVariable String keyWord){
        productRequest productRequest = productService.getProductByKeyWord(keyWord);
        return new ResponseEntity<productRequest>(productRequest,HttpStatus.OK);
    }
}
