package com.curso.autoticketapp.common.infrastructure.exceptions;

import com.curso.autoticketapp.common.domain.exception.ExceptionWithCode;
import com.curso.autoticketapp.common.domain.exception.HandlerNotFoundException;
import com.curso.autoticketapp.common.domain.exception.NoValidJwtException;
import com.curso.autoticketapp.common.domain.exception.TokenException;
import com.curso.autoticketapp.user.domain.exception.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionMessage {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest req, MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                req.getRequestURI(),
                errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            HandlerNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest req, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                req.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            UserAlreadyExistsException.class
    })
    @ResponseBody
    public ErrorMessage conflict(HttpServletRequest req, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                req.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({TokenException.class, NoValidJwtException.class})
    @ResponseBody
    public ErrorMessage handleToken(HttpServletRequest req, ExceptionWithCode exception) {
        return new ErrorMessage(
                exception.getCode(),
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                req.getRequestURI()
        );
   }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ErrorMessage handleBadCredential(HttpServletRequest req, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                req.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({LockedException.class, DisabledException.class})
    @ResponseBody
    public ErrorMessage handleLockedOrDisabled(HttpServletRequest req, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                req.getRequestURI()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorMessage handleUnexpected(HttpServletRequest req, Exception exception) {
        return new ErrorMessage(
                exception.getMessage(),
                exception.getClass().getSimpleName(),
                req.getRequestURI()
        );
    }
}
