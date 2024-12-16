package com.example.springcommerce.repository;

import com.example.springcommerce.entity.cartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cartItemRepo extends JpaRepository<cartItemsEntity, Long> {
    @query("SELECT ci FROM carts ci WHERE ci.cart.id = ?1 AND ci.product.id = ?2")
    cartItemsEntity findCartItemByProductIDAndCartId(Long cartId, Long productId);

}
