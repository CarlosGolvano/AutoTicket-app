package com.curso.autoticketapp.ticket.infrastructure.database.repository;

import com.curso.autoticketapp.common.domain.pagination.PaginationQuery;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryTicketRepository extends JpaRepository<TicketEntity, Long> {

    Page<TicketEntity> findAll(Pageable pageable, Specification<TicketEntity> specification);

}
