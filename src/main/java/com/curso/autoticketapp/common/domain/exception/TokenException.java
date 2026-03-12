package com.curso.autoticketapp.common.domain.exception;

public class TokenException extends ExceptionWithCode {

    public TokenException(String message) {

        super(message, "TOKEN_EXPIRED");
    }
}
