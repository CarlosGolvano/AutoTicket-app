package com.curso.autoticketapp.ticket.domain.port;

import com.curso.autoticketapp.ticket.domain.entity.Ticket;

public interface TicketRepository {

    Ticket upsert(Ticket ticket);

}
