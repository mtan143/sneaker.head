package com.flutter.sneaker.head.controller.auth;

import lombok.Data;

@Data
public class SignUpRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private String cellphone;
    private String address;
}
