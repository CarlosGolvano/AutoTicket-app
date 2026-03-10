package com.curso.autoticketapp.user.application.command.login;

import com.curso.autoticketapp.common.application.mediator.Request;
import lombok.Data;

@Data
public class LoginUserRequest implements Request<LoginUserResponse> {

    private String email;
    private String password;

}
