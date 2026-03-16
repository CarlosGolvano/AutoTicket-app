package com.curso.autoticketapp.ticket.domain.entity;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class Ticket {

    private Long id;
    private UUID uuid;
    private String publicId;
    private String subject;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private Date createdAt;
    private Date updatedAt;

    private Long userId;
}
