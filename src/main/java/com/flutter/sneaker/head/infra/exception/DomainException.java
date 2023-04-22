package com.flutter.sneaker.head.infra.exception;

public class DomainException extends RuntimeException{

    public DomainException(DomainErrorCode errorCode) {
        super(errorCode.name());
    }
}
