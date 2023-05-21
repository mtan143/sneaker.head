package com.flutter.sneaker.head.infra.repo;

import com.flutter.sneaker.head.infra.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long> {

    List<ProductSizeEntity> findAllByProductId(String productId);

    Optional<ProductSizeEntity> findByProductIdAndSizeId(String productId, String sizeId);

    Optional<ProductSizeEntity> findByProductSizeId(String productSizeId);
}
