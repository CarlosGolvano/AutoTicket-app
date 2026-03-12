package com.curso.autoticketapp.ticket.infrastructure.database.repository;

import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.port.TicketRepository;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;
import com.curso.autoticketapp.ticket.infrastructure.database.mapper.TicketEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final QueryTicketRepository ticketRepository;

    private final TicketEntityMapper ticketEntityMapper;

    @Override
    public Ticket upsert(Ticket ticket) {
        TicketEntity ticketEntity = ticketEntityMapper.mapToTicketEntity(ticket);
        Long ticket_id = ticket.getId();

        if (ticket_id != null) {
            Optional<TicketEntity> optionalTicket = ticketRepository.findById(ticket_id);
            optionalTicket.ifPresent(entity -> ticketEntity.setId(entity.getId()));
        }

        TicketEntity ticketSaved = ticketRepository.save(ticketEntity);

        return ticketEntityMapper.mapToTicket(ticketSaved);
    }
}
