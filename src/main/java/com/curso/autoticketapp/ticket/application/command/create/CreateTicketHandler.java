package com.curso.autoticketapp.ticket.application.command.create;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.entity.TicketStatus;
import com.curso.autoticketapp.ticket.domain.port.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateTicketHandler implements RequestHandler<CreateTicketRequest, CreateTicketResponse> {

    private final TicketRepository ticketRepository;

    @Override
    public CreateTicketResponse handle(CreateTicketRequest request) {

        Ticket ticket = Ticket.builder()
                .subject(request.getSubject())
                .description(request.getDescription())
                .user_id(request.getUser_id())
                .status(TicketStatus.WAIT_FOR_ANALYSIS)
                .build();

        Ticket upsert = ticketRepository.upsert(ticket);

        return new CreateTicketResponse(upsert.getId());
    }

    @Override
    public Class<CreateTicketRequest> getRequestType() {
        return CreateTicketRequest.class;
    }
}
