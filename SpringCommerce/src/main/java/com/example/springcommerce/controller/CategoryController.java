package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Request.categoryRequest;
import com.example.springcommerce.DTO.Response.CategoryResponse;
import com.example.springcommerce.config.AppConstants;
import com.example.springcommerce.service.categoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category API", description = "Endpoints for managing categories")
public class CategoryController {

    private final categoryService categoryService;

    @Autowired
    public CategoryController(categoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "page", defaultValue = AppConstants.PAGE_NUMBER, required = false) int page,
                                                             @RequestParam(name = "size", defaultValue = AppConstants.PAGE_SIZE, required = false) int size,
                                                             @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sort,
                                                             @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy
    ) {
        CategoryResponse categories = categoryService.getAllCategories(page, size, sortBy, sort);
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @Operation(summary = "Add a new category", description = "Add a new category to the database")
    public ResponseEntity<categoryRequest> addCategory(@Valid @RequestBody categoryRequest category) {
        categoryRequest response = categoryService.addCategory(category);
        return new ResponseEntity<categoryRequest>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "Update an existing category in the database")
    public ResponseEntity<categoryRequest> updateCategory(@Valid @RequestBody categoryRequest category, @PathVariable Long id) {
        categoryRequest updatedCategory = categoryService.updateCategory(category, id);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete a category from the database")
    public ResponseEntity<categoryRequest> deleteCategory(@PathVariable Long id) {
        categoryRequest response = categoryService.deleteCategory(id);
        return ResponseEntity.ok(response);
    }
}