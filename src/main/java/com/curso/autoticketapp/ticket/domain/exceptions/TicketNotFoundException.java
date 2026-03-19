package com.curso.autoticketapp.ticket.domain.exceptions;

import java.util.UUID;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String public_id) {
        super("Ticket with id " + public_id + " not found.");
    }

    public TicketNotFoundException(Long id) {
        super("Ticket with private id " + id + " not found.");
    }

    public TicketNotFoundException(UUID uuid) {
        super("Ticket with UUID " + uuid + " not found.");
    }
}
