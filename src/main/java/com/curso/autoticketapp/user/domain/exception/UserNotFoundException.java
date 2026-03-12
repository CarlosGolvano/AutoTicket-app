package com.curso.autoticketapp.user.domain.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String email) {
        super("User not found with email: " + email);
    }
}

