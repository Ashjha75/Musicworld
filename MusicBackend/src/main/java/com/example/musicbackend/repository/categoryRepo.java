package com.example.musicbackend.repository;

import com.example.musicbackend.entity.categoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepo extends JpaRepository<categoryEntity, Long> {
}
