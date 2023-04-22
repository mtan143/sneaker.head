package com.flutter.sneaker.head.infra.repo;

import com.flutter.sneaker.head.infra.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
