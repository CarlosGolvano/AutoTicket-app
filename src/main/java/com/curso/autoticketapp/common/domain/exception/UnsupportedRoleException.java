package com.curso.autoticketapp.common.domain.exception;

import com.curso.autoticketapp.common.domain.enums.UserRole;

import java.util.List;

public class UnsupportedRoleException extends RuntimeException {
    public UnsupportedRoleException(UserRole role) {
        super("Unsupported role: " + role);
    }

    public UnsupportedRoleException(List<UserRole> roles) {
        super("Unsupported roles: " + roles);
    }
}
