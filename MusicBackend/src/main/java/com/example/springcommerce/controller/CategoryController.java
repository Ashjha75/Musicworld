package com.example.springcommerce.controller;

import com.example.springcommerce.DTO.Response.CategoryResponse;
import com.example.springcommerce.entity.categoryEntity;
import com.example.springcommerce.service.categoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final categoryService categoryService;

    @Autowired
    public CategoryController(categoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryResponse> getAllCategories() {
        CategoryResponse categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<String> addCategory(@Valid @RequestBody categoryEntity category) {
        String response = categoryService.addCategory(category);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<categoryEntity> updateCategory(@Valid @RequestBody categoryEntity category, @PathVariable Long id) {
        categoryEntity updatedCategory = categoryService.updateCategory(category, id);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        String response = categoryService.deleteCategory(id);
        return ResponseEntity.ok(response);
    }
}