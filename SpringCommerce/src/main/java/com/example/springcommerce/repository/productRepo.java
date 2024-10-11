package com.example.springcommerce.repository;

import com.example.springcommerce.entity.categoryEntity;
import com.example.springcommerce.entity.productEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepo extends JpaRepository<productEntity,Long> {
    Page<productEntity> findByCategoryOrderByPriceAsc(categoryEntity category, Pageable pageDetails);

    Page<productEntity> findByProductNameLikeIgnoreCase(String keyWord, Pageable pageDetails);
}
