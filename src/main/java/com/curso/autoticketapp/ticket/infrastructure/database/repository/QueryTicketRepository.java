package com.curso.autoticketapp.ticket.infrastructure.database.repository;

import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryTicketRepository extends JpaRepository<TicketEntity, Long> {
}
