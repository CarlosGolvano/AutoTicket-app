package com.curso.autoticketapp.common.domain.exception;

public class HandlerNotFoundException extends RuntimeException {
    public HandlerNotFoundException(Class<?> request) {
        super("No handler found for request: " + request);
    }
}
