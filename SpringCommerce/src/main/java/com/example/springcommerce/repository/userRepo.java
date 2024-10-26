package com.example.springcommerce.repository;

import com.example.springcommerce.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<userEntity, Long> {
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<userEntity> findByUsername(String username);
}