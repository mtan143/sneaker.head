package com.flutter.sneaker.head.controller.auth;

import lombok.Data;

@Data
public class SignInRequest {

    private String usernameOrEmail;
    private String password;
}
