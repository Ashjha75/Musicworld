package com.example.springcommerce.repository;

import com.example.springcommerce.entity.cartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cartRepo extends JpaRepository<cartEntity, Long> {
}
