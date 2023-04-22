package com.flutter.sneaker.head.infra.repo;

import com.flutter.sneaker.head.infra.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long> {

    List<ProductSizeEntity> findAllByProductId(String productId);
}
