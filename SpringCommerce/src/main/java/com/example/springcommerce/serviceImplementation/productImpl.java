package com.example.springcommerce.serviceImplementation;

import com.example.springcommerce.DTO.Request.productRequest;
import com.example.springcommerce.DTO.Response.productResponse;
import com.example.springcommerce.entity.categoryEntity;
import com.example.springcommerce.entity.productEntity;
import com.example.springcommerce.exception.ApiException;
import com.example.springcommerce.exception.ResourceNotFound;
import com.example.springcommerce.repository.categoryRepo;
import com.example.springcommerce.repository.productRepo;
import com.example.springcommerce.service.fileService;
import com.example.springcommerce.service.productService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class productImpl implements productService {

    private final productRepo productRepository;
    private final categoryRepo categoryRepository;
    private final ModelMapper modelMapper;
    private final fileService fileService;

    @Value("${project.image}")
    private String path;

    @Autowired
    public productImpl(productRepo productRepository, categoryRepo categoryRepository, ModelMapper modelMapper, fileService fileService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.fileService = fileService;
    }

    @Override
    public productRequest addProduct(productRequest productRequest, Long categoryId) {
        categoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound(categoryId, "Category", "categoryId"));

        boolean isProductNotPresent = true;

        List<productEntity> products = category.getProducts();
        for (productEntity productEntity : products) {
            if (productEntity.getProductName().equals((productRequest.getProductName()))) {
                isProductNotPresent = false;
                break;
            }
        }

        if (isProductNotPresent) {
            productEntity product = modelMapper.map(productRequest, productEntity.class);
            product.setImage("defaultImage.jpg");
            product.setCategory(category);
            double specialPrice = (product.getDiscount() * 0.01) * product.getPrice();
            product.setSpecialPrice(specialPrice);
            productEntity savedProduct = productRepository.save(product);
            return modelMapper.map(savedProduct, productRequest.class);
        } else {
            throw new ApiException("Product Already Exists");
        }
    }

    @Override
    public productResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<productEntity> pageProducts = productRepository.findAll(pageDetails);

        List<productEntity> products = pageProducts.getContent();
        if (products.isEmpty()) {
            throw new ApiException("No Product Found!");
        }
        List<productRequest> productRequests = products.stream().map(entity -> modelMapper.map(entity, productRequest.class)).toList();

        productResponse productResponse = new productResponse();
        productResponse.setProducts(productRequests);
        productResponse.setPageNumber(pageProducts.getNumber());
        productResponse.setPageSize(pageProducts.getSize());
        productResponse.setTotalElments(pageProducts.getTotalElements());
        productResponse.setTotalPages(pageProducts.getTotalPages());
        productResponse.setLastPage(pageProducts.isLast());
        return productResponse;
    }

    @Override
    public productResponse getProductByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        categoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound(categoryId, "categoryId", "Category"));
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<productEntity> pageProducts = productRepository.findByCategoryOrderByPriceAsc(category, pageDetails);
        List<productEntity> products = pageProducts.getContent();
        if (products.isEmpty()) {
            throw new ApiException("No Product Found!");
        }
        List<productRequest> productRequests = products.stream().map(entity -> modelMapper.map(entity, productRequest.class)).toList();
        productResponse productResponse = new productResponse();
        productResponse.setProducts(productRequests);
        productResponse.setProducts(productRequests);
        productResponse.setPageNumber(pageProducts.getNumber());
        productResponse.setPageSize(pageProducts.getSize());
        productResponse.setTotalElments(pageProducts.getTotalElements());
        productResponse.setTotalPages(pageProducts.getTotalPages());
        productResponse.setLastPage(pageProducts.isLast());
        return productResponse;
    }

    @Override
    public productResponse getProductByKeyWord(String keyWord, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<productEntity> pageProducts = productRepository.findByProductNameLikeIgnoreCase("%" + keyWord + "%", pageDetails);
        List<productEntity> products = pageProducts.getContent();
        if (products.isEmpty()) {
            throw new ApiException("No Product Found!");
        }
        List<productRequest> productRequests = products.stream().map(entity -> modelMapper.map(entity, productRequest.class)).toList();
        productResponse productResponse = new productResponse();
        productResponse.setProducts(productRequests);
        productResponse.setProducts(productRequests);
        productResponse.setPageNumber(pageProducts.getNumber());
        productResponse.setPageSize(pageProducts.getSize());
        productResponse.setTotalElments(pageProducts.getTotalElements());
        productResponse.setTotalPages(pageProducts.getTotalPages());
        productResponse.setLastPage(pageProducts.isLast());
        return productResponse;
    }

    @Override
    public productRequest updateProduct(productRequest productRequest, Long productId) {
//        get the existing product
        productEntity existingProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFound(productId, "productId", "Product"));

        productEntity product = modelMapper.map(productRequest, productEntity.class);
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
    public productRequest updateProductImage(Long productId, MultipartFile image) throws IOException {
//        Get the Product from DB
        productEntity savedProduct = productRepository.findById((productId)).orElseThrow(() -> new ResourceNotFound(productId, "productId", "Product"));
//        Upload image to server

//        filename of uploaded image
        String imagePath = path;
        String filename = fileService.uploadImage(imagePath, image);
//        update new file name to product
        savedProduct.setImage(filename);
//        save the product
        productEntity updatedProduct = productRepository.save(savedProduct);
//        return Dto after mapping product Dto
        return modelMapper.map(updatedProduct, productRequest.class);


    }


}