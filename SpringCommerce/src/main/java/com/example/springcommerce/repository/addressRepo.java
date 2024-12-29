package com.example.springcommerce.repository;

import com.example.springcommerce.entity.addressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface addressRepo extends JpaRepository<addressEntity, Long> {
    List<addressEntity> findByUserEmail(String email);
}
