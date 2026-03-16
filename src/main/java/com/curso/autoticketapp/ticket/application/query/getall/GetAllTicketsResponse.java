package com.curso.autoticketapp.ticket.application.query.getall;

import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;

public record GetAllTicketsResponse(PaginationResult<Ticket> ticketsPage) {}
