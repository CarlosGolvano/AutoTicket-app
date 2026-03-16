package com.curso.autoticketapp.it.ticket.command.create;

import com.curso.autoticketapp.it.common.AuthUtils;
import com.curso.autoticketapp.it.user.config.RestConfig;
import com.curso.autoticketapp.ticket.infrastructure.api.TicketController;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(RestConfig.class)
public class CreateTicketIT {

    @Autowired
    @Qualifier("restTemplate")
    private TestRestTemplate restTemplate;

    @Test
    @Sql(value = "/it/user/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/it/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void createTicket() {
        String token = AuthUtils.login(restTemplate, "test@example.es", "1234");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("subject", "Test subject");
        params.add("description", "Test description");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<CreateTicketResponseDTO> response = restTemplate.exchange(
                TicketController.BASE_URL,
                HttpMethod.POST,
                request,
                CreateTicketResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().publicId());
    }

}