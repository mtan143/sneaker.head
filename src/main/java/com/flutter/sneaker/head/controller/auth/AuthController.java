package com.flutter.sneaker.head.controller.auth;

import com.flutter.sneaker.head.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signIn")
    public SignInResponse signInResponse(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PostMapping("/signUp")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }
}
