package com.curso.autoticketapp.it.ticket.query.getall;

import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.it.common.AuthUtils;
import com.curso.autoticketapp.it.user.config.RestConfig;
import com.curso.autoticketapp.ticket.infrastructure.api.TicketController;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.TicketDTO;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(RestConfig.class)
public class GetAllTicketsIT {

    @Autowired
    @Qualifier("restTemplate")
    private TestRestTemplate restTemplate;

    @Test
    @Sql(value = "/it/user/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/ticket/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllTicketsAgent() {
        String token = AuthUtils.login(restTemplate, "agent@example.es", "1234");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<PaginationResult<TicketDTO>> response = restTemplate.exchange(
                TicketController.BASE_URL,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {});


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getContent());
        assertEquals(2, response.getBody().getTotalElements());
    }

    @Test
    @Sql(value = "/it/user/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/ticket/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllTicketsClient() {
        String token = AuthUtils.login(restTemplate, "client@example.es", "1234");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        ResponseEntity<PaginationResult<TicketDTO>> response = restTemplate.exchange(
                TicketController.BASE_URL,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {});


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getContent());
        assertEquals(1, response.getBody().getTotalElements());
    }
}
