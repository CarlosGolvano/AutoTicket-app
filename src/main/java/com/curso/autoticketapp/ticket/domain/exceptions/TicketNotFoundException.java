package com.curso.autoticketapp.ticket.domain.exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String public_id) {
        super("Ticket with id " + public_id + " not found.");
    }
}
