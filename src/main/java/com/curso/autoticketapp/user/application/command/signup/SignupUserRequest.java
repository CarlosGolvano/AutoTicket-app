package com.curso.autoticketapp.user.application.command.signup;

import com.curso.autoticketapp.common.application.mediator.Request;
import lombok.Data;

@Data
public class SignupUserRequest implements Request<SignupUserResponse> {

    private String username;
    private String email;
    private String password;

}
