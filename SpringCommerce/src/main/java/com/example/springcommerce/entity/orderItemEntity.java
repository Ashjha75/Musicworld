package com.example.springcommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orderItems")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private productEntity product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private orderEntity order;

    private Integer quantity;
    private Double discount;
    private Double orderProductPrice;
}
