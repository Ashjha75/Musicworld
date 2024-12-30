package com.example.springcommerce.repository;

import com.example.springcommerce.entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepo extends JpaRepository<orderEntity, Long> {
}
