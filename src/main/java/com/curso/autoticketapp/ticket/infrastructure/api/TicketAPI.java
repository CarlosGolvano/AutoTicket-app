package com.curso.autoticketapp.ticket.infrastructure.api;

import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketStatus;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketDTO;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketResponseDTO;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.TicketDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestHeader;

public interface TicketAPI {

    ResponseEntity<CreateTicketResponseDTO> createTicket(CreateTicketDTO createTicketDTO, String token);

    ResponseEntity<PaginationResult<TicketDTO>> getAllTickets(
            int pageNumber,
            int pageSize,
            String sortBy,
            String direction,
            String subject,
            String description,
            TicketStatus status,
            TicketPriority priority,
            String token,
            Authentication authentication
    );

    ResponseEntity<TicketDTO> getTicketByPublicId(String public_id, String token, Authentication authentication);

}
