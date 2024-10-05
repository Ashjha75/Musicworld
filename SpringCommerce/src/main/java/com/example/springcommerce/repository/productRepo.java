package com.example.springcommerce.repository;

import com.example.springcommerce.entity.productEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends JpaRepository<productEntity,Long> {
}
