package com.flutter.sneaker.head.service.account;

import com.flutter.sneaker.head.controller.account.AccountRequest;
import com.flutter.sneaker.head.controller.account.AccountResponse;
import com.flutter.sneaker.head.infra.entity.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAll();

    AccountResponse updateAccount(AccountRequest accountRequest);

    void checkAccountEligibility(String accountNumber);

    AccountResponse toggleAccount(AccountRequest accountRequest);

    AccountEntity findByAccountNumber(String accountNumber);
}
