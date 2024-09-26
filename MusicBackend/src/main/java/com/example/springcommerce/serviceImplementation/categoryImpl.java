package com.example.springcommerce.serviceImplementation;


import com.example.springcommerce.DTO.Response.CategoryResponse;
import com.example.springcommerce.entity.categoryEntity;
import com.example.springcommerce.exception.ApiException;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.categoryRepo;
import com.example.springcommerce.service.categoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryImpl implements categoryService {
    private final categoryRepo categoryRepos;

    public categoryImpl(categoryRepo categoryRepo) {
        this.categoryRepos = categoryRepo;
    }


    @Override
    public CategoryResponse getAllCategories() {
        return categoryRepos.findAll();
    }

    @Override
    public String addCategory(categoryEntity category) {
        categoryEntity existingCategory = categoryRepos.findByCategoryName(category.getCategoryName());
        if (existingCategory != null)
            throw new ApiException("Category with name " + category.getCategoryName() + " already exists");
        categoryRepos.save(category);
        return "Category added successfully";
    }

    @Override
    public categoryEntity updateCategory(categoryEntity category, Long categoryId) {
        // Find the category by id
        categoryEntity existingCategory = categoryRepos.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound(categoryId, "Category", "categoryId"));
        if (existingCategory.getCategoryName().equals(category.getCategoryName()))
            throw new ApiException("Category with name " + category.getCategoryName() + " already exists");

        // Update the fields of the existing category
        existingCategory.setCategoryName(category.getCategoryName());

        // Save the updated category
        existingCategory = categoryRepos.save(existingCategory);
        return existingCategory;
    }

    @Override
    public String deleteCategory(Long categoryId) {
//        find the category by id
        categoryEntity existingCategory = categoryRepos.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound(categoryId, "Category", "categoryId"));

        categoryRepos.delete(existingCategory);
        return "Category with id " + categoryId + " deleted successfully";
    }
}
