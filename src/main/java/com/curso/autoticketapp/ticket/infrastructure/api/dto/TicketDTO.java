package com.curso.autoticketapp.ticket.infrastructure.api.dto;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TicketDTO {

    private Long id;
    private String subject;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private Date createdAt;
    private Date updatedAt;
    private Long user_id;

}
