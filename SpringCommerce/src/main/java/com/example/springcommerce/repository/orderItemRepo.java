package com.example.springcommerce.repository;

import com.example.springcommerce.entity.orderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderItemRepo extends JpaRepository<orderItemEntity, Long> {
}
