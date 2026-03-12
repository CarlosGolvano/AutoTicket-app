package com.curso.autoticketapp.user.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupUserRequestDTO {

    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String username;

}
