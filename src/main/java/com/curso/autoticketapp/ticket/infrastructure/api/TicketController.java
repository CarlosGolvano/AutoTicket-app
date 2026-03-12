package com.curso.autoticketapp.ticket.infrastructure.api;

import com.curso.autoticketapp.common.application.mediator.Mediator;
import com.curso.autoticketapp.common.infrastructure.services.JwtService;
import com.curso.autoticketapp.ticket.application.command.create.CreateTicketRequest;
import com.curso.autoticketapp.ticket.application.command.create.CreateTicketResponse;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketDTO;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketResponseDTO;
import com.curso.autoticketapp.ticket.infrastructure.api.mapper.TicketMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TicketController.BASE_URL)
@Tag(name = "Ticket", description = "Ticket API operations")
@SecurityRequirement(name = "Bearer Authentication")
@RequiredArgsConstructor
public class TicketController implements TicketAPI {

    public static final String BASE_URL = "/api/v1/tickets";

    private final Mediator mediator;

    private final TicketMapper ticketMapper;

    private final JwtService jwtService;

    @PostMapping
    @Override
    @PreAuthorize("hasAnyAuthority('CLIENT', 'AGENT')")
    public ResponseEntity<CreateTicketResponseDTO> createTicket(
            @ModelAttribute CreateTicketDTO createTicketDTO,
            @RequestHeader(name = "Authorization") String token
    ) {
        Long user_id = jwtService.getUserId(token.substring(7));

        CreateTicketRequest request = ticketMapper.mapToCreateTicketRequest(createTicketDTO);
        request.setUser_id(user_id);

        CreateTicketResponse response = mediator.dispatch(request);

        return ResponseEntity.ok(ticketMapper.mapToCreateTicketResponseDTO(response));
    }
}
