package com.curso.autoticketapp.user.application.command.signup;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupUserResponse {

    private Long id;
    private String token;

}
