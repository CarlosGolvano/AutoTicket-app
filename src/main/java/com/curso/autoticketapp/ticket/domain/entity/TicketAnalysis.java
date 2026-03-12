package com.curso.autoticketapp.ticket.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TicketAnalysis {

    private Long id;
    private TicketCategory category;
    private TicketSentiment sentiment;
    private TicketUrgency urgency;
    private Date createdAt;
    private Date updatedAt;

    private Ticket ticket;

}
