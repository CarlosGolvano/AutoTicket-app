package com.curso.autoticketapp.user.application.command.signin;

import com.curso.autoticketapp.common.application.mediator.Request;
import lombok.Data;

@Data
public class SigninUserRequest implements Request<SigninUserResponse> {

    private String username;
    private String email;
    private String password;

}
