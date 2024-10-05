package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.entity.categoryEntity;
import com.example.springcommerce.entity.productEntity;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.categoryRepo;
import com.example.springcommerce.repository.productRepo;
import com.example.springcommerce.service.productService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productImpl implements productService {

    private final productRepo productRepository;
    private final categoryRepo categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public productImpl(productRepo productRepository, categoryRepo categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public productRequest addProduct(productEntity productEntity, Long categoryId) {
        categoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound(categoryId, "Category", "categoryId"));
        productEntity.setImage("defaultImage.jpg");
        productEntity.setCategory(category);
        double specialPrice = (productEntity.getDiscount() * 0.01) * productEntity.getPrice();
        productEntity.setSpecialPrice(specialPrice);

        productEntity savedProduct = productRepository.save(productEntity);

        return modelMapper.map(savedProduct, productRequest.class);
    }
}