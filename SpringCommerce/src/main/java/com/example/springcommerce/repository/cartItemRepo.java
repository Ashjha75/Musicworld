package com.example.springcommerce.repository;

import com.example.springcommerce.entity.cartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface cartItemRepo extends JpaRepository<cartItemsEntity, Long> {

    @Query("SELECT ci FROM cartItemsEntity ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2")
    cartItemsEntity findCartItemByProductIDAndCartId(Long cartId, Long productId);

    @Modifying
    @Transactional
    @Query("DELETE FROM cartItemsEntity ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2")
    void deleteCartItemByProductIDAndCartId(Long cartId, Long productId);
}