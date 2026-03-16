package com.curso.autoticketapp.ticket.domain.entity;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketCategory;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketSentiment;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketUrgency;
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

    private Long ticketId;

}
