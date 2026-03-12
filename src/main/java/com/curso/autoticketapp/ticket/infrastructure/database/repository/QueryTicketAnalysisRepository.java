package com.curso.autoticketapp.ticket.infrastructure.database.repository;

import com.curso.autoticketapp.ticket.domain.entity.TicketAnalysis;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface QueryTicketAnalysisRepository extends JpaRepository<TicketAnalysisEntity, Long> {
}
