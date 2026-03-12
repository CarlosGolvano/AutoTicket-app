package com.curso.autoticketapp.user.infrastructure.authentication;

import com.curso.autoticketapp.common.infrastructure.services.JwtService;
import com.curso.autoticketapp.user.domain.port.AuthenticationPort;
import com.curso.autoticketapp.user.infrastructure.database.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Authenticate implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public String authenticate(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        UserEntity user = (UserEntity) authenticate.getPrincipal();

        assert user != null;
        return jwtService.generateToken(user);
    }
}
