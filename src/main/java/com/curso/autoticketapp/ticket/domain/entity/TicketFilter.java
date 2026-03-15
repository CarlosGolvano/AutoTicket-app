package com.curso.autoticketapp.ticket.domain.entity;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketFilter {

    private String subject;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;

}
