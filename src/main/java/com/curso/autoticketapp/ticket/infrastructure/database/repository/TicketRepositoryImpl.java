package com.curso.autoticketapp.ticket.infrastructure.database.repository;

import com.curso.autoticketapp.common.domain.pagination.PaginationQuery;
import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.application.query.getall.GetAllTicketsResponse;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.entity.TicketFilter;
import com.curso.autoticketapp.ticket.domain.port.TicketRepository;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;
import com.curso.autoticketapp.ticket.infrastructure.database.mapper.TicketEntityMapper;
import com.curso.autoticketapp.ticket.infrastructure.specification.TicketSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public PaginationResult<Ticket> findAll(PaginationQuery paginationQuery, TicketFilter ticketFilter) {
        PageRequest pageRequest = PageRequest.of(
                paginationQuery.getPage(),
                paginationQuery.getSize(),
                Sort.by(Sort.Direction.fromString(paginationQuery.getDirection()),
                        paginationQuery.getSortBy())
        );

        Specification<TicketEntity> specification = Specification.allOf(
                TicketSpecification.byPriority(ticketFilter.getPriority())
                        .and(TicketSpecification.byPriority(ticketFilter.getPriority()))
        );

        Page<TicketEntity> page = ticketRepository.findAll(pageRequest, specification);

        return new PaginationResult<>(
                page.getContent()
                        .stream()
                        .map(ticketEntityMapper::mapToTicket)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }


}
