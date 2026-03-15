package com.curso.autoticketapp.ticket.application.query.getall;

import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllTicketsResponse {

    private PaginationResult<Ticket> ticketsPage;

}
