package com.example.springcommerce.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderItemRequest {
    private  Long orderItemId;
    private productRequest product;
    private Integer quantity;
    private Double discount;
    private Double orderProductPrice;
}
