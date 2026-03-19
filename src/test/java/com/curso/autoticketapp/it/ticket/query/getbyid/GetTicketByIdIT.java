package com.curso.autoticketapp.it.ticket.query.getbyid;

import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.it.common.AuthUtils;
import com.curso.autoticketapp.it.user.config.RestConfig;
import com.curso.autoticketapp.ticket.infrastructure.api.TicketController;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.TicketDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.MultiValueMap;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(RestConfig.class)
public class GetTicketByIdIT {

    @Autowired
    @Qualifier("restTemplate")
    private TestRestTemplate restTemplate;

    @Test
    @Sql(value = "/it/user/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/ticket/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getTicketByIdAgent() {
        String token = AuthUtils.login(restTemplate, "agent@example.es", "1234");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<TicketDTO> response = restTemplate.exchange(
                TicketController.BASE_URL + "/BBBBB",
                HttpMethod.GET,
                request,
                TicketDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ticket de prueba agente", response.getBody().subject());
        assertEquals(UUID.fromString("50d0a907-daaf-4a0d-aef5-72568095419b"), response.getBody().uuid());
        assertEquals(3, response.getBody().userId());

        response = restTemplate.exchange(
                TicketController.BASE_URL + "/AAAAA",
                HttpMethod.GET,
                request,
                TicketDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ticket de prueba cliente", response.getBody().subject());
        assertEquals(UUID.fromString("972f55d4-19ae-411a-b154-67a01831252b"), response.getBody().uuid());
        assertEquals(2, response.getBody().userId());
    }

    @Test
    @Sql(value = "/it/user/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/ticket/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getTicketByIdClient() {
        String token = AuthUtils.login(restTemplate, "client@example.es", "1234");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<TicketDTO> response = restTemplate.exchange(
                TicketController.BASE_URL + "/AAAAA",
                HttpMethod.GET,
                request,
                TicketDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ticket de prueba cliente", response.getBody().subject());
        assertEquals(UUID.fromString("972f55d4-19ae-411a-b154-67a01831252b"), response.getBody().uuid());
        assertEquals(2, response.getBody().userId());

        response = restTemplate.exchange(
                TicketController.BASE_URL + "/BBBBB",
                HttpMethod.GET,
                request,
                TicketDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
