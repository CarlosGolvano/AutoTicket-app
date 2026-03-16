package com.curso.autoticketapp.ticket.infrastructure.api.dto;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketStatus;

import java.util.Date;
import java.util.UUID;

public record TicketDTO(
        UUID uuid,
        String publicId,
        String subject,
        String description,
        TicketStatus status,
        TicketPriority priority,
        Date createdAt,
        Date updatedAt,
        Long userId) {}
