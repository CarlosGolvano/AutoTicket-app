package com.curso.autoticketapp.common.domain.exception;

import lombok.Getter;

@Getter
public class NoValidJwtException extends ExceptionWithCode {

    public NoValidJwtException(Exception e) {

        super("Invalid JWT token or mal formed.", "TOKEN_INVALID", e);
    }

}
