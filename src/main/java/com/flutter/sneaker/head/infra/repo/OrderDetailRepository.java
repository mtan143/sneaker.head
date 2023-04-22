package com.flutter.sneaker.head.infra.repo;

import com.flutter.sneaker.head.infra.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
}
