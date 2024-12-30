package com.example.springcommerce.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class paymentRequest {

    private Long paymentId;
    private String paymentMethod;
    private String paymentGatewayPaymentStatus;
    private String paymentGatewayPaymentId;
    private String paymentGatewayResponseMessage;
    private String paymentGatewayName;
}
