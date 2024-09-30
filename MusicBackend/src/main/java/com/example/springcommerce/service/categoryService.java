package com.example.springcommerce.service;


import com.example.springcommerce.DTO.Request.categoryRequest;
import com.example.springcommerce.DTO.Response.CategoryResponse;
import com.example.springcommerce.entity.categoryEntity;

import java.util.List;

public interface categoryService {
    CategoryResponse getAllCategories(Integer page, Integer size, String sort, String sortBy);

    categoryRequest addCategory(categoryRequest category);

    categoryRequest updateCategory(categoryRequest category, Long categoryId);

    categoryRequest deleteCategory(Long categoryId);
}
