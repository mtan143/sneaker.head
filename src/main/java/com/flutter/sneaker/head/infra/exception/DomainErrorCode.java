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
    ACCOUNT_NOT_FOUND("007", "Account not found!"),
    ACCOUNT_NOT_ELIGIBILITY("008", "Account is not eligibility!"),
    PRODUCT_SIZE_NOT_FOUND("009", "Product size not found!"),
    ORDER_INFO_MISSING("010", "Order info is missing!"),
    PRODUCT_AMOUNT_IS_INSUFFICIENT("011", "Product amount not enough!"),
    TOTAL_PRICE_NOT_MATCH("012", "Total price not match!"),
    ORDER_NOT_FOUND("013", "Order not found!")
    ;

    private final String value;
    private final String message;
}
