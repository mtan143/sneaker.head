package com.flutter.sneaker.head.service.auth;

import com.flutter.sneaker.head.controller.auth.SignInRequest;
import com.flutter.sneaker.head.controller.auth.SignInResponse;
import com.flutter.sneaker.head.controller.auth.SignUpRequest;
import com.flutter.sneaker.head.controller.auth.SignUpResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    SignInResponse signIn(@RequestBody SignInRequest signInRequest);
    SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest);
}
