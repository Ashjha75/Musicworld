package com.example.springcommerce.service;


import com.example.springcommerce.DTO.Request.categoryRequest;
import com.example.springcommerce.DTO.Response.CategoryResponse;
import com.example.springcommerce.entity.categoryEntity;

import java.util.List;

public interface categoryService {
    CategoryResponse getAllCategories();

    categoryRequest addCategory(categoryRequest category);

    categoryEntity updateCategory(categoryEntity category, Long categoryId);

    String deleteCategory(Long categoryId);
}
