package com.curso.autoticketapp.it;

import com.curso.autoticketapp.it.config.RestConfig;
import com.curso.autoticketapp.user.infrastructure.api.UserController;
import com.curso.autoticketapp.user.infrastructure.api.dto.LoginUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SigninUserRequestDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.SigninUserResponseDTO;
import com.curso.autoticketapp.user.infrastructure.api.dto.TokenResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(RestConfig.class)
public class UserIT {

    @Autowired
    @Qualifier("restTemplate")
    private TestRestTemplate restTemplate;

    @Value("${jwt.token}")
    private String token;

    @Test
    public void userSignin() {
        SigninUserRequestDTO request = new SigninUserRequestDTO();
        request.setUsername("test");
        request.setEmail("test@example.es");
        request.setPassword("1234");

        ResponseEntity<SigninUserResponseDTO> response = restTemplate.postForEntity(
                UserController.BASE_URL + "/signin",
                request,
                SigninUserResponseDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(token, response.getBody().getToken());
    }

    @Sql(value = "/it/user/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void userLogin() {
        LoginUserRequestDTO request = new LoginUserRequestDTO();
        request.setEmail("test@example.es");
        request.setPassword("1234");

        ResponseEntity<TokenResponseDTO> response = restTemplate.postForEntity(
                UserController.BASE_URL + "/login",
                request,
                TokenResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(token, response.getBody().getToken());
    }
}
