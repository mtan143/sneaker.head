package com.flutter.sneaker.head.infra.repo;

import com.flutter.sneaker.head.infra.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);
    Optional<RoleEntity> findByRoleId(String roleId);
}
