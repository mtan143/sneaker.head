package com.flutter.sneaker.head.infra.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DomainErrorCode {

    ROLE_NOT_FOUND("001", "Role not found!"),
    SIGN_UP_ERROR("002", "Error while processing sign up!"),
    SIGN_IN_ERROR("003", "Error while processing sign in!"),
    PRODUCT_NOT_FOUND("004", "Product not found!"),
    SIZE_NOT_FOUND("005", "Size not found!"),
    CATEGORY_NOT_FOUND("006", "Category not found!"),
    ACCOUNT_NOT_FOUND("007", "Account not found!")
    ;

    private final String value;
    private final String message;
}
