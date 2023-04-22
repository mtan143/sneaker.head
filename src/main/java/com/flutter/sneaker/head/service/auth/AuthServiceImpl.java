package com.flutter.sneaker.head.service.auth;

import com.flutter.sneaker.head.controller.auth.SignInRequest;
import com.flutter.sneaker.head.controller.auth.SignInResponse;
import com.flutter.sneaker.head.controller.auth.SignUpRequest;
import com.flutter.sneaker.head.controller.auth.SignUpResponse;
import com.flutter.sneaker.head.infra.entity.AccountEntity;
import com.flutter.sneaker.head.infra.entity.RoleEntity;
import com.flutter.sneaker.head.infra.enumeration.AccountStatus;
import com.flutter.sneaker.head.infra.exception.DomainErrorCode;
import com.flutter.sneaker.head.infra.exception.DomainException;
import com.flutter.sneaker.head.infra.repo.AccountRepository;
import com.flutter.sneaker.head.infra.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsernameOrEmail(),
                    signInRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception exception) {
            throw new DomainException(DomainErrorCode.SIGN_IN_ERROR);
        }
        return SignInResponse.builder()
                .message("User logged in successfully!")
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        if(accountRepository.existsByUserName(signUpRequest.getUsername())){
            return SignUpResponse.builder()
            .message("Username is already taken!")
            .status(HttpStatus.BAD_REQUEST)
            .build();
        }

        if(accountRepository.existsByEmail(signUpRequest.getEmail())){
            return SignUpResponse.builder()
            .message("Email is already taken!")
            .status(HttpStatus.BAD_REQUEST)
            .build();
        }

        try {
        RoleEntity role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new DomainException(DomainErrorCode.ROLE_NOT_FOUND));

        // create user object
        AccountEntity user = AccountEntity.builder()
                .accountNumber(UUID.randomUUID().toString())
                .accountName(signUpRequest.getName())
                .userName(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .cellphone(signUpRequest.getCellphone())
                .address(signUpRequest.getAddress())
                .status(AccountStatus.ACTIVE)
                .roleId(role.getRoleId())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
        accountRepository.save(user);

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return SignUpResponse.builder()
                .message("User registered successfully!")
                .status(HttpStatus.OK)
                .build();
    }
}