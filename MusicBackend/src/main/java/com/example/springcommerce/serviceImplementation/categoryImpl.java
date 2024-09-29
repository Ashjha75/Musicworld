package com.example.springcommerce.serviceImplementation;


import com.example.springcommerce.DTO.Request.categoryRequest;
import com.example.springcommerce.DTO.Response.CategoryResponse;
import com.example.springcommerce.entity.categoryEntity;
import com.example.springcommerce.exception.ApiException;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.categoryRepo;
import com.example.springcommerce.service.categoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryImpl implements categoryService {
    private final categoryRepo categoryRepos;
    private final ModelMapper modelMapper;

    @Autowired
    public categoryImpl(categoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepos = categoryRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryResponse getAllCategories() {
        List<categoryEntity> categories = categoryRepos.findAll();
        if (categories.isEmpty())
            throw new ApiException("No categories found");

        List<categoryRequest> categoryRequests = categories.stream()
                .map(category -> modelMapper.map(category, categoryRequest.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategories(categoryRequests);

        return categoryResponse;
    }

    @Override
    public categoryRequest addCategory(categoryRequest category) {
        categoryEntity categoryEntity = modelMapper.map(category, categoryEntity.class);
        categoryEntity existingCategory = categoryRepos.findByCategoryName(category.getCategoryName());
        if (existingCategory != null)
            throw new ApiException("Category with name " + category.getCategoryName() + " already exists");
        categoryEntity savedCategory = categoryRepos.save(categoryEntity);
        return modelMapper.map(savedCategory, categoryRequest.class);
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
