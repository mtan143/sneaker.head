package com.flutter.sneaker.head.service.account;

import com.flutter.sneaker.head.controller.account.AccountResponse;
import com.flutter.sneaker.head.infra.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public List<AccountResponse> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
