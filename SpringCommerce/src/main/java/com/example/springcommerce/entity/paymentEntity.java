package com.example.springcommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class paymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne(mappedBy = "payment", cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    private orderEntity order;

    @NotBlank
    @Size(min=4,message = "Payment method must be at least 4 characters")
    private String paymentMethod;

    private String paymentGatewayPaymentStatus;
    private String paymentGatewayPaymentId;
    private String paymentGatewayResponseMessage;
    private String paymentGatewayName;


    public paymentEntity(String paymentMethod, String paymentGatewayPaymentId, String paymentGatewayPaymentStatus, String paymentGatewayResponseMessage,String paymentGatewayName) {

        this.paymentGatewayPaymentStatus = paymentGatewayPaymentStatus;
        this.paymentGatewayPaymentId = paymentGatewayPaymentId;
        this.paymentGatewayResponseMessage = paymentGatewayResponseMessage;
        this.paymentGatewayName = paymentGatewayName;
        this.paymentMethod = paymentMethod;

    }
}
