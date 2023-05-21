package com.flutter.sneaker.head.infra.repo;

import com.flutter.sneaker.head.infra.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByEmail(String email);
    Optional<AccountEntity> findByUserNameOrEmail(String username, String email);
    Optional<AccountEntity> findByUserName(String username);
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
}
