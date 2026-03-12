package com.curso.autoticketapp.user.domain.port;

public interface AuthenticationPort {

    String authenticate(String username, String password);

}
