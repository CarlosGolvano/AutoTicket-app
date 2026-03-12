package com.curso.autoticketapp.it.user;

import com.curso.autoticketapp.common.infrastructure.services.JwtService;
import com.curso.autoticketapp.it.user.config.RestConfig;
import com.curso.autoticketapp.user.infrastructure.api.UserController;
import com.curso.autoticketapp.user.infrastructure.api.dto.LoginUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SignupUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SignupUserResponseDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.TokenResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(RestConfig.class)
public class UserIT {

    @Autowired
    @Qualifier("restTemplate")
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    private final String email = "test@example.es";


    @Sql(value = "/it/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void userSignup() {

        SignupUserRequestDTO request = new SignupUserRequestDTO();
        request.setUsername("test");
        request.setEmail(email);
        request.setPassword("1234");

        ResponseEntity<SignupUserResponseDTO> response = restTemplate.postForEntity(
                UserController.BASE_URL + "/signup",
                request,
                SignupUserResponseDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        String token = response.getBody().getToken();
        assertNotNull(token);
        assertEquals(email, jwtService.getUsername(token));
    }

    @Sql(value = "/it/user/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void userLogin() {
        LoginUserRequestDTO request = new LoginUserRequestDTO();
        request.setEmail(email);
        request.setPassword("1234");

        ResponseEntity<TokenResponseDTO> response = restTemplate.postForEntity(
                UserController.BASE_URL + "/login",
                request,
                TokenResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        String token = response.getBody().getToken();
        assertNotNull(token);
        assertEquals(email, jwtService.getUsername(token));
    }
}