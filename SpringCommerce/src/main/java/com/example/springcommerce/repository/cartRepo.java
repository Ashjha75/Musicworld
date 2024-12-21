package com.example.springcommerce.repository;

import com.example.springcommerce.entity.cartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface cartRepo extends JpaRepository<cartEntity, Long> {

    @Query("SELECT c FROM cartEntity c WHERE c.user.email = ?1")
    cartEntity findCartByEmail(String email);
}
