package com.curso.autoticketapp.user.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginUserRequestDTO {

    @Email
    private String email;
    private String password;

}
