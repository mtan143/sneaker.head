package com.flutter.sneaker.head.service.account;

import com.flutter.sneaker.head.infra.entity.AccountEntity;
import com.flutter.sneaker.head.infra.entity.RoleEntity;
import com.flutter.sneaker.head.infra.exception.DomainErrorCode;
import com.flutter.sneaker.head.infra.exception.DomainException;
import com.flutter.sneaker.head.infra.repo.AccountRepository;
import com.flutter.sneaker.head.infra.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        AccountEntity user = accountRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));
        RoleEntity role = roleRepository.findByRoleId(user.getRoleId())
                .orElseThrow(() -> new DomainException(DomainErrorCode.ROLE_NOT_FOUND));

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);
    }
}