package com.curso.autoticketapp;

import com.curso.autoticketapp.common.application.mediator.Mediator;
import com.curso.autoticketapp.user.application.command.login.LoginUserRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Pruebas {

    private final Mediator mediator;

    public void run(String... args) throws Exception {
        mediator.dispatch(new LoginUserRequest());
    }
}
