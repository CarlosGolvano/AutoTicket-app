package com.curso.autoticketapp.ticket.application.query.getbyid;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.common.domain.enums.UserRole;
import com.curso.autoticketapp.common.domain.exception.UnsupportedRoleException;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.exceptions.TicketNotFoundException;
import com.curso.autoticketapp.ticket.domain.port.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetTicketByPublicIdHandler implements RequestHandler<GetTicketByPublicIdRequest, GetTicketByPublicIdResponse> {

    private final TicketRepository repository;

    @Override
    public GetTicketByPublicIdResponse handle(GetTicketByPublicIdRequest request) {

        Ticket ticket = repository.findByPublicId(request.publicId())
                .orElseThrow(() -> new TicketNotFoundException(request.publicId()));

        if (request.roles().contains(UserRole.AGENT)) {
            return new GetTicketByPublicIdResponse(ticket);
        } else if (request.roles().contains(UserRole.CLIENT)) {
            if (ticket.getUserId().equals(request.userId())) {
                return new GetTicketByPublicIdResponse(ticket);
            } else {
                throw new TicketNotFoundException(request.publicId());
            }
        } else {
            throw new UnsupportedRoleException(request.roles());
        }
    }

    @Override
    public Class<GetTicketByPublicIdRequest> getRequestType() {
        return GetTicketByPublicIdRequest.class;
    }
}
