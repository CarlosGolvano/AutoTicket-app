package com.curso.autoticketapp.common.domain.exception;

import lombok.Getter;

@Getter
public class ExceptionWithCode extends RuntimeException {

    private final String code;

    public ExceptionWithCode(String message, String code) {
        super(message);
        this.code = code;
    }

    public ExceptionWithCode(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
