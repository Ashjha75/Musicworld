package com.example.springcommerce.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long orderId;

    @Email
    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "order" , cascade ={CascadeType.PERSIST, CascadeType.MERGE} ,orphanRemoval = true)
    private List<orderItemEntity> orderItems =new ArrayList<>();

    private LocalDate orderDate;

//    @OneToOne
//    @JoinColumn(name = "payment_id")
//    private paymentEntity payment;

    private Double totalAmount;
    private String orderStatus;

//    Reference to address
    @ManyToOne
    @JoinColumn(name = "address_id")
    private addressEntity address;


}
