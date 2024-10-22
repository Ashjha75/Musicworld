package com.example.springcommerce.repository;

import com.example.springcommerce.entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for userEntity.
 * Extends JpaRepository to provide CRUD operations for userEntity.
 */
@Repository
public interface userRepo extends JpaRepository<userEntity, Long> {

    /**
     * Finds a userEntity by its username.
     *
     * @param username the username of the userEntity to find
     * @return an Optional containing the found userEntity, or an empty Optional if no userEntity was found
     */
    Optional<userEntity> findByUsername(String username);
}