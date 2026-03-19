package com.curso.autoticketapp.ticket.application.command.create.ticket;

import java.util.UUID;

public record CreateTicketResponse(UUID uuid, String publicId) {}
