package com.example.springcommerce.repository;

import com.example.springcommerce.entity.categoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepo extends JpaRepository<categoryEntity, Long> {
    categoryEntity findByCategoryName(String categoryName);
}
