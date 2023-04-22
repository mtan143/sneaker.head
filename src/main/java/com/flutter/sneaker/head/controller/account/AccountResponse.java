package com.flutter.sneaker.head.controller.account;

import com.flutter.sneaker.head.infra.entity.AccountEntity;
import com.flutter.sneaker.head.infra.enumeration.AccountStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountResponse {

    private long id;
    private String accountName;
    private String accountNumber;
    private String cellphone;
    private String address;
    private AccountStatus status;
    private String roleId;
    private LocalDateTime createdDate;

    public static AccountResponse fromEntity(AccountEntity accountEntity) {
        return AccountResponse.builder()
                .id(accountEntity.getId())
                .accountName(accountEntity.getAccountName())
                .accountNumber(accountEntity.getAccountNumber())
                .cellphone(accountEntity.getCellphone())
                .address(accountEntity.getAddress())
                .status(accountEntity.getStatus())
                .roleId(accountEntity.getRoleId())
                .createdDate(accountEntity.getCreatedDate())
                .build();
    }
}
