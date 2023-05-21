package com.flutter.sneaker.head.infra.repo;

import com.flutter.sneaker.head.infra.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<SizeEntity, Long> {

    Optional<SizeEntity> findBySizeId(String sizeId);
    Optional<SizeEntity> findBySize(Integer size);
}
