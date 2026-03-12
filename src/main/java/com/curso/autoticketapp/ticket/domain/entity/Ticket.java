package com.curso.autoticketapp.ticket.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Ticket {

    private Long id;
    private String subject;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private Date createdAt;
    private Date updatedAt;

    private Long user_id;
}
