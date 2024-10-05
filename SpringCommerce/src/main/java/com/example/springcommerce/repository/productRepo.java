package com.example.springcommerce.repository;

import com.example.springcommerce.entity.categoryEntity;
import com.example.springcommerce.entity.productEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepo extends JpaRepository<productEntity,Long> {
    List<productEntity> findByCategoryOrderByPriceAsc(categoryEntity category);

    List<productEntity> findByProductNameLikeIgnoreCase(String keyWord);
}
