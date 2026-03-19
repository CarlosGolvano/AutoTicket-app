package com.curso.autoticketapp.ticket.domain.port;

import com.curso.autoticketapp.ticket.domain.entity.TicketAnalysis;

public interface TicketAnalysisRepository {

    TicketAnalysis upsert(TicketAnalysis ticketAnalysis);

}
