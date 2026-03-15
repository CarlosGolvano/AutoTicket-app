package com.curso.autoticketapp.it.common;

import com.curso.autoticketapp.user.infrastructure.api.UserController;
import com.curso.autoticketapp.user.infrastructure.api.dto.LoginUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.TokenResponseDTO;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthUtils {

    public static String login(TestRestTemplate restTemplate, String email, String password) {
        LoginUserRequestDTO loginRequest = new LoginUserRequestDTO();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        ResponseEntity<TokenResponseDTO> loginResponse = restTemplate.postForEntity(
                UserController.BASE_URL + "/login",
                loginRequest,
                TokenResponseDTO.class);

        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        assertNotNull(loginResponse.getBody());

        String token = loginResponse.getBody().getToken();
        assertNotNull(token);

        return token;
    }
}