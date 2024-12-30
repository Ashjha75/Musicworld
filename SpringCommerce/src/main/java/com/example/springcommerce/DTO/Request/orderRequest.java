package com.example.springcommerce.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderRequest {
    private Long orderId;
    private String email;
    private List<orderItemRequest> orderItems;
    private paymentRequest payment;
    private Double totalAmount;
    private String orderStatus;
    private Long addressId;
}
