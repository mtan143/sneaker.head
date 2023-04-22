package com.flutter.sneaker.head.infra.repo;

import com.flutter.sneaker.head.infra.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findByCategoryId(String categoryId, Pageable pageable);

    Optional<ProductEntity> findByProductId(String productId);

    @Override
    Page<ProductEntity> findAll(Pageable pageable);

    long countByCategoryId(String categoryId);
}
