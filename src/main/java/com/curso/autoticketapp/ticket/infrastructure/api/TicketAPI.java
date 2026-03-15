package com.curso.autoticketapp.ticket.infrastructure.api;

import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketDTO;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TicketAPI {

    ResponseEntity<CreateTicketResponseDTO> createTicket(CreateTicketDTO createTicketDTO, String token);

    ResponseEntity<PaginationResult<>> getAllTickets(
            int pageNumber,
            int pageSize,
            String sortBy,
            String direction,

    );

}
