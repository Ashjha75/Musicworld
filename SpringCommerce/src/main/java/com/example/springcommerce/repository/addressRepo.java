package com.example.springcommerce.repository;

import com.example.springcommerce.entity.addressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface addressRepo extends JpaRepository<addressEntity, Long> {
}
