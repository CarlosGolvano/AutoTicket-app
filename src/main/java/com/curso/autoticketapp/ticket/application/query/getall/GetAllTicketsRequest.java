package com.curso.autoticketapp.ticket.application.query.getall;

import com.curso.autoticketapp.common.application.mediator.Request;
import com.curso.autoticketapp.common.domain.pagination.PaginationQuery;
import com.curso.autoticketapp.ticket.domain.entity.TicketFilter;
import lombok.Data;

@Data
public class GetAllTicketsRequest implements Request<GetAllTicketsResponse> {

    private PaginationQuery paginationQuery;
    private TicketFilter ticketFilter;

}
