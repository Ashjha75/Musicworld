package com.example.springcommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class cartItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_itemid")
    private Long cartItemid;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private cartEntity cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private productEntity product;

    private Integer quantity;
    private double discount;
    private double productPrice;

}
