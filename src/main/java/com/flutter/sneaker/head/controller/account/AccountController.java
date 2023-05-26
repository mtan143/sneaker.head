package com.flutter.sneaker.head.controller.account;

import com.flutter.sneaker.head.infra.entity.AccountEntity;
import com.flutter.sneaker.head.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<AccountResponse> getAllAccount() {
        return accountService.getAll();
    }

    @PostMapping
    public AccountResponse upsert(@RequestBody AccountRequest accountRequest) {
        return accountService.updateAccount(accountRequest);
    }

    @GetMapping("/{accountNumber}")
    public AccountEntity getProfileInformation(@PathVariable String accountNumber) {
        return accountService.getProfileInformation(accountNumber);
    }
}
