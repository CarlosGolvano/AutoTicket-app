package com.curso.autoticketapp.user.application.command.login;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.user.domain.port.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {

    private final AuthenticationPort authentication;

    @Override
    public LoginUserResponse handle(LoginUserRequest request) {
        String token = authentication.authenticate(request.getEmail(), request.getPassword());
        return new LoginUserResponse(token);
    }

    @Override
    public Class<LoginUserRequest> getRequestType() {
        return LoginUserRequest.class;
    }
}
