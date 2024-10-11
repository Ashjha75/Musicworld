package com.example.springcommerce.service;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.DTO.Response.productResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface productService {

    productRequest addProduct(productRequest productEntity, Long categoryId);

    productResponse getProductByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    productResponse getProductByKeyWord(String keyWord, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    productRequest updateProduct(productRequest productEntity, Long productId);

    productRequest deleteProduct(Long productId);

    productRequest updateProductImage(Long productId, MultipartFile image) throws IOException;

    productResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
