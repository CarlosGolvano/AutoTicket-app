package com.curso.autoticketapp.ticket.infrastructure.database.repository;

import com.curso.autoticketapp.common.domain.pagination.PaginationQuery;
import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.entity.TicketAnalysis;
import com.curso.autoticketapp.ticket.domain.entity.TicketFilter;
import com.curso.autoticketapp.ticket.domain.port.TicketAnalysisRepository;
import com.curso.autoticketapp.ticket.domain.port.TicketRepository;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketAnalysisEntity;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;
import com.curso.autoticketapp.ticket.infrastructure.database.mapper.TicketAnalysisEntityMapper;
import com.curso.autoticketapp.ticket.infrastructure.database.mapper.TicketEntityMapper;
import com.curso.autoticketapp.ticket.infrastructure.specification.TicketSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketAnalysisRepositoryImpl implements TicketAnalysisRepository {

    private final QueryTicketAnalysisRepository ticketAnalysisRepository;

    private final TicketAnalysisEntityMapper ticketAnalysisEntityMapper;

    @Override
    public TicketAnalysis upsert(TicketAnalysis ticketAnalysis) {
        TicketAnalysisEntity ticketAnalysisEntity = ticketAnalysisEntityMapper.mapToTicketAnalysisEntity(ticketAnalysis);

        if (ticketAnalysis.getId() != null) {
            Optional<TicketAnalysisEntity> optionalTicketAnalysis = ticketAnalysisRepository.findById(ticketAnalysis.getId());
            optionalTicketAnalysis.ifPresent(entity -> ticketAnalysisEntity.setId(entity.getId()));
        }

        TicketAnalysisEntity ticketAnalysisSaved = ticketAnalysisRepository.save(ticketAnalysisEntity);

        return ticketAnalysisEntityMapper.mapToTicketAnalysis(ticketAnalysisSaved);
    }

}
