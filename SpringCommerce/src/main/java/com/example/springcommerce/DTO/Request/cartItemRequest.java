package com.example.springcommerce.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class cartItemRequest {
    private Long cartItemId;
    private cartRequest cart;
    private productRequest product;
    private Integer quantity;
    private Double discount;
    private Double productPrice;
}
