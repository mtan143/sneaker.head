package com.flutter.sneaker.head.controller.account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {

    private String id;
    private String accountName;
    private String cellphone;
    private String address;
    private String email;
}
