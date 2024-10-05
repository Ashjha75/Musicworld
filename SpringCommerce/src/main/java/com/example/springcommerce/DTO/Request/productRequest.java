package com.example.springcommerce.DTO.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class productRequest {

    private Long productId;
    private String productName;
    private String image;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialPrice;

}
