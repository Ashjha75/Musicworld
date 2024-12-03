package com.example.springcommerce.repository;

import com.example.springcommerce.entity.cartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cartItemRepo extends JpaRepository<cartItemsEntity, Long> {
}
