package com.curso.autoticketapp.ticket.application.query.getall;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.common.domain.enums.UserRole;
import com.curso.autoticketapp.common.domain.exception.UnsupportedRoleException;
import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.port.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetAllTicketsHandler implements RequestHandler<GetAllTicketsRequest, GetAllTicketsResponse> {

    private final TicketRepository repository;

    @Override
    public GetAllTicketsResponse handle(GetAllTicketsRequest request) {
        List<UserRole> roles = request.roles();
        PaginationResult<Ticket> tickets;

        if (roles.contains(UserRole.AGENT)) {
            tickets = repository.findAllTickets(request.paginationQuery(), request.ticketFilter());
        } else if (roles.contains(UserRole.CLIENT)) {
            tickets = repository.findAllTicketsByUserId(request.paginationQuery(), request.ticketFilter(), request.userId());
        } else {
            throw new UnsupportedRoleException(roles);
        }

        return new GetAllTicketsResponse(tickets);
    }

    @Override
    public Class<GetAllTicketsRequest> getRequestType() {
        return GetAllTicketsRequest.class;
    }
}
