package com.flutter.sneaker.head.service.account;

import com.flutter.sneaker.head.controller.account.AccountRequest;
import com.flutter.sneaker.head.controller.account.AccountResponse;
import com.flutter.sneaker.head.infra.entity.AccountEntity;
import com.flutter.sneaker.head.infra.enumeration.AccountStatus;
import com.flutter.sneaker.head.infra.exception.DomainErrorCode;
import com.flutter.sneaker.head.infra.exception.DomainException;
import com.flutter.sneaker.head.infra.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public AccountResponse updateAccount(AccountRequest accountRequest) {
        AccountEntity account = accountRepository.findByEmail(accountRequest.getEmail())
                .orElseThrow(() -> new DomainException(DomainErrorCode.ACCOUNT_NOT_FOUND));

        account.setAccountName(accountRequest.getAccountName());
        account.setCellphone(accountRequest.getCellphone());
        account.setAddress(accountRequest.getAddress());
        account.setLastModifiedDate(LocalDateTime.now());

        return AccountResponse.fromEntity(accountRepository.save(account));
    }

    @Override
    public void checkAccountEligibility(String accountNumber) {
        AccountEntity account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new DomainException(DomainErrorCode.ACCOUNT_NOT_FOUND));

        if (account.getAccountName().isEmpty() && AccountStatus.ACTIVE != account.getStatus()) {
            throw new DomainException(DomainErrorCode.ACCOUNT_NOT_ELIGIBILITY);
        }
    }

    @Override
    public AccountResponse toggleAccount(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public AccountEntity findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new DomainException(DomainErrorCode.ACCOUNT_NOT_FOUND));
    }

    @Override
    public AccountEntity getProfileInformation(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new DomainException(DomainErrorCode.ACCOUNT_NOT_FOUND));
    }
}
