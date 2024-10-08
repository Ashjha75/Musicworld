package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.DTO.Response.productResponse;
import com.example.springcommerce.entity.categoryEntity;
import com.example.springcommerce.entity.productEntity;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.categoryRepo;
import com.example.springcommerce.repository.productRepo;
import com.example.springcommerce.service.productService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public productRequest addProduct(productRequest productRequest, Long categoryId) {
        categoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound(categoryId, "Category", "categoryId"));
        productEntity product = modelMapper.map(productRequest,productEntity.class);
        product.setImage("defaultImage.jpg");
        product.setCategory(category);
        double specialPrice = (product.getDiscount() * 0.01) * product.getPrice();
        product.setSpecialPrice(specialPrice);

        productEntity savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, productRequest.class);
    }

    @Override
    public productResponse getAllProducts() {
        List<productEntity> products = productRepository.findAll();
        List<productRequest> productRequests = products.stream().map(entity -> modelMapper.map(entity, productRequest.class)).toList();

        productResponse productResponse = new productResponse();
        productResponse.setProducts(productRequests);
        return productResponse;
    }

    @Override
    public productResponse getProductByCategory(Long categoryId) {
        categoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound(categoryId, "categoryId", "Category"));
        List<productEntity> products = productRepository.findByCategoryOrderByPriceAsc(category);
        List<productRequest> productRequests = products.stream().map(entity -> modelMapper.map(entity, productRequest.class)).toList();
        productResponse productResponse = new productResponse();
        productResponse.setProducts(productRequests);
        return productResponse;
    }

    @Override
    public productResponse getProductByKeyWord(String keyWord) {
        List<productEntity> products = productRepository.findByProductNameLikeIgnoreCase("%" + keyWord + "%");
        List<productRequest> productRequests = products.stream().map(entity -> modelMapper.map(entity, productRequest.class)).toList();
        productResponse productResponse = new productResponse();
        productResponse.setProducts(productRequests);
        return productResponse;
    }

    @Override
    public productRequest updateProduct(productRequest productRequest, Long productId) {
//        get the existing product
        productEntity existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound(productId, "productId", "Product"));
        productEntity product = modelMapper.map(productRequest,productEntity.class);
//        update the product
        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setQuantity(product.getQuantity());
        double specialPrice = (product.getDiscount() * 0.01) * product.getPrice();
        existingProduct.setSpecialPrice(specialPrice);
//        save the product
        productEntity savedProduct = productRepository.save(existingProduct);

//        return the updated product
        return modelMapper.map(savedProduct, productRequest.class);
    }

    @Override
    public productRequest deleteProduct(Long productId) {
        productEntity existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound(productId, "productId", "Product"));

        productRepository.delete(existingProduct);

        return modelMapper.map(existingProduct, productRequest.class);
    }

    @Override
    public productRequest updateProductImage(Long productId, MultipartFile image) {
        return null;
    }

}