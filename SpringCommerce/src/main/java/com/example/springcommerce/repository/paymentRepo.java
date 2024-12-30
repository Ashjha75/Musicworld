package com.example.springcommerce.repository;

import com.example.springcommerce.entity.paymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface paymentRepo extends JpaRepository<paymentEntity, Long> {
}
