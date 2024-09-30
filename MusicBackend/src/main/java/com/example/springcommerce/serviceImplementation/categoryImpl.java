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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryImpl implements categoryService {
    private final categoryRepo categoryRepos;
    private final ModelMapper modelMapper;

    @Autowired
    public categoryImpl(categoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepos = gi
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoryResponse getAllCategories(Integer page, Integer size, String sort, String sortBy) {

        Sort sortByAndOrder = sort.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

//        pagination
        Pageable pageDetails = PageRequest.of(page, size);
        Page<categoryEntity> categories = categoryRepos.findAll(pageDetails);

        if (categories.isEmpty())
            throw new ApiException("No categories found");

        List<categoryRequest> categoryRequests = categories.stream()
                .map(category -> modelMapper.map(category, categoryRequest.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategories(categoryRequests);
        categoryResponse.setPageNumber(categories.getNumber());
        categoryResponse.setPageSize(categories.getSize());
        categoryResponse.setTotalPages(categories.getTotalPages());
        categoryResponse.setTotalElements(categories.getTotalElements());
        categoryResponse.setLast(categories.isLast());
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
    public categoryRequest updateCategory(categoryRequest category, Long categoryId) {
        // Find the category by id
        categoryEntity existingCategory = categoryRepos.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound(categoryId, "Category", "categoryId"));

        // Check if the new category name already exists
        categoryEntity categoryWithName = categoryRepos.findByCategoryName(category.getCategoryName());
        if (categoryWithName != null && !categoryWithName.getCategoryId().equals(categoryId)) {
            throw new ApiException("Category with name " + category.getCategoryName() + " already exists");
        }

        // Update the fields of the existing category
        existingCategory.setCategoryName(category.getCategoryName());

        // Save the updated category
        categoryEntity savedCategory = categoryRepos.save(existingCategory);
        return modelMapper.map(savedCategory, categoryRequest.class);
    }

    @Override
    public categoryRequest deleteCategory(Long categoryId) {
//        find the category by id
        categoryEntity existingCategory = categoryRepos.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound(categoryId, "Category", "categoryId"));

        categoryRepos.delete(existingCategory);
        return modelMapper.map(existingCategory, categoryRequest.class);
    }
}
