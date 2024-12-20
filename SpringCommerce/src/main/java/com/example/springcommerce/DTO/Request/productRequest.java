package com.example.springcommerce.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class productRequest {

    private Long productId;

    @NotBlank(message = "Product name must not be blank")
    private String productName;

    @NotBlank(message = "Image must not be blank")
    private String image;

    @NotBlank(message = "Description must not be blank")
    private String description;

    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;
}