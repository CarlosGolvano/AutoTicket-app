package com.curso.autoticketapp.user.application.command.login;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {

    @Override
    public LoginUserResponse handle(LoginUserRequest request) {
        LoginUserResponse response = new LoginUserResponse();
        response.setToken("OKEY");

        return response;
    }

    @Override
    public Class<LoginUserRequest> getRequestType() {
        return LoginUserRequest.class;
    }
}
