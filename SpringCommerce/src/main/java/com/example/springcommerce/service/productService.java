package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.DTO.Response.productResponse;

public interface productService {

    productRequest addProduct(productRequest productEntity, Long categoryId);

    productResponse getAllProducts();

    productResponse getProductByCategory(Long categoryId);

    productResponse getProductByKeyWord(String keyWord);

    productRequest updateProduct(productRequest productEntity, Long productId);

    productRequest deleteProduct(Long productId);
}
