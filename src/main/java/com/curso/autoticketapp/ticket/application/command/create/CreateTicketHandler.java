package com.curso.autoticketapp.ticket.application.command.create;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.common.infrastructure.util.IdUtils;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketStatus;
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
                .uuid(IdUtils.getUUID())
                .publicId(IdUtils.getAlphanumericPublicId())
                .subject(request.getSubject())
                .description(request.getDescription())
                .userId(request.getUserId())
                .status(TicketStatus.WAIT_FOR_ANALYSIS)
                .build();

        Ticket upsert = ticketRepository.upsert(ticket);

        return new CreateTicketResponse(upsert.getPublicId());
    }

    @Override
    public Class<CreateTicketRequest> getRequestType() {
        return CreateTicketRequest.class;
    }
}
