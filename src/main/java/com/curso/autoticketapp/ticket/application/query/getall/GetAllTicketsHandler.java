package com.curso.autoticketapp.ticket.application.query.getall;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.port.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetAllTicketsHandler implements RequestHandler<GetAllTicketsRequest, GetAllTicketsResponse> {

    private final TicketRepository repository;

    @Override
    public GetAllTicketsResponse handle(GetAllTicketsRequest request) {

        PaginationResult<Ticket> tickets = repository.findAll(request.getPaginationQuery(), request.getTicketFilter());

        return new GetAllTicketsResponse(tickets);
    }

    @Override
    public Class<GetAllTicketsRequest> getRequestType() {
        return GetAllTicketsRequest.class;
    }
}
