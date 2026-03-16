package com.curso.autoticketapp.user.domain.entity;

import com.curso.autoticketapp.common.domain.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private Instant createdAt;
    private Instant updatedAt;

}
