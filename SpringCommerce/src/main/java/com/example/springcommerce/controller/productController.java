package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.DTO.Response.productResponse;
import com.example.springcommerce.config.AppConstants;
import com.example.springcommerce.service.productService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class productController {

    private final productService productService;

    @Autowired
    public productController(productService productService) {
        this.productService = productService;
    }

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<productRequest> addProduct(@Valid @RequestBody productRequest productRequest, @PathVariable Long categoryId) {
        productRequest products = productService.addProduct(productRequest, categoryId);
        return new ResponseEntity<productRequest>(products, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<productResponse> getAllProducts(@RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                          @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                          @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
                                                          @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        productResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<productResponse>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<productResponse> getProductByCategory(@PathVariable Long categoryId,
                                                                @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                                @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
                                                                @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        productResponse productResponse = productService.getProductByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<productResponse>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyWord/{keyWord}")
    public ResponseEntity<productResponse> getProductByKeyWord(@PathVariable String keyWord,
                                                               @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                               @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                               @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PRODUCTS_BY, required = false) String sortBy,
                                                               @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        productResponse productResponse = productService.getProductByKeyWord(keyWord, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<productResponse>(productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/productId/{productId}")
    public ResponseEntity<productRequest> updateProduct(@Valid @RequestBody productRequest productRequest, @PathVariable Long productId) {
        productRequest savedProduct = productService.updateProduct(productRequest, productId);
        return new ResponseEntity<productRequest>(savedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/productId/{productId}")
    public ResponseEntity<productRequest> deleteProduct(@PathVariable Long productId) {
        productRequest product = productService.deleteProduct(productId);
        return new ResponseEntity<productRequest>(product, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<productRequest> updateProductImage(@PathVariable Long productId, @RequestParam("image") MultipartFile image) throws IOException {
        productRequest product = productService.updateProductImage(productId, image);
        return new ResponseEntity<productRequest>(product, HttpStatus.OK);
    }

}
