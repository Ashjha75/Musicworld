package com.example.springcommerce.service;


import com.example.springcommerce.DTO.Response.CategoryResponse;
import com.example.springcommerce.entity.categoryEntity;

import java.util.List;

public interface categoryService {
    CategoryResponse getAllCategories();

    String addCategory(categoryEntity category);

    categoryEntity updateCategory(categoryEntity category, Long categoryId);

    String deleteCategory(Long categoryId);
}
