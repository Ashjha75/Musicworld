package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.DTO.Response.productResponse;
import com.example.springcommerce.entity.productEntity;

public interface productService {

    productRequest addProduct(productEntity productEntity, Long categoryId);

    productResponse getAllProducts();

    productResponse getProductByCategory(Long categoryId);
}
