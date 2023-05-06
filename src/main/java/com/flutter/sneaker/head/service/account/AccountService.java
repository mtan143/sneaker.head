package com.flutter.sneaker.head.service.account;

import com.flutter.sneaker.head.controller.account.AccountRequest;
import com.flutter.sneaker.head.controller.account.AccountResponse;

import java.util.List;

public interface AccountService {
    List<AccountResponse> getAll();

    AccountResponse updateAccount(AccountRequest accountRequest);

    AccountResponse toggleAccount(AccountRequest accountRequest);
}
