package com.example.springcommerce.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class cartRequest {
    private Long cartId;
    private Double totalPrice = 0.00;
    private List<productRequest> cartItems = new ArrayList<>();
}
