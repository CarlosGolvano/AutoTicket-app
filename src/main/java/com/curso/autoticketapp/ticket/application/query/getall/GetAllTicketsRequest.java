package com.curso.autoticketapp.ticket.application.query.getall;

import com.curso.autoticketapp.common.application.mediator.Request;
import com.curso.autoticketapp.common.domain.enums.UserRole;
import com.curso.autoticketapp.common.domain.pagination.PaginationQuery;
import com.curso.autoticketapp.ticket.domain.entity.TicketFilter;

import java.util.List;

public record GetAllTicketsRequest(
        PaginationQuery paginationQuery,
        TicketFilter ticketFilter,
        Long userId,
        List<UserRole> roles
) implements Request<GetAllTicketsResponse> {}
