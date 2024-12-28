package com.example.springcommerce.repository;

import com.example.springcommerce.entity.cartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cartRepo extends JpaRepository<cartEntity, Long> {

    @Query("SELECT c FROM cartEntity c WHERE c.user.email = ?1")
    cartEntity findCartByEmail(String email);

    @Query("SELECT c FROM cartEntity c WHERE c.user.email = ?1 AND c.cartId = ?2")
    cartEntity findCartByEmailAndCartId(String email, Long cartId);

    @Query("SELECT c FROM cartEntity c  JOIN FETCH c.cartItems ci JOIN FETCH ci.product p where p.productId = ?1")
    List<cartEntity> findCartsByProductId(Long productId);
}
