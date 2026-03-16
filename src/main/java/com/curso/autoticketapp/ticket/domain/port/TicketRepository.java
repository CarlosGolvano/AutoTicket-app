package com.curso.autoticketapp.ticket.domain.port;

import com.curso.autoticketapp.common.domain.pagination.PaginationQuery;
import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.entity.TicketFilter;

import java.util.Optional;

public interface TicketRepository {

    Ticket upsert(Ticket ticket);

    PaginationResult<Ticket> findAllTickets(PaginationQuery paginationQuery, TicketFilter ticketFilter);

    PaginationResult<Ticket> findAllTicketsByUserId(PaginationQuery paginationQuery, TicketFilter ticketFilter, Long userId);

    Optional<Ticket> findByPublicId(String publicId);

    Optional<Ticket> findByPublicIdAndUserId(String publicId, Long userId);

}
