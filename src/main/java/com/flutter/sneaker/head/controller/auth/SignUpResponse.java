package com.flutter.sneaker.head.controller.auth;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SignUpResponse {

    private String message;
    private HttpStatus status;
}
