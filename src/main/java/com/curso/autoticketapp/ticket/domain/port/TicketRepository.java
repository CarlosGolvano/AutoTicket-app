package com.curso.autoticketapp.ticket.domain.port;

import com.curso.autoticketapp.common.domain.pagination.PaginationQuery;
import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.entity.TicketFilter;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;

public interface TicketRepository {

    Ticket upsert(Ticket ticket);

    PaginationResult<Ticket> findAll(PaginationQuery paginationQuery, TicketFilter ticketFilter);

}
