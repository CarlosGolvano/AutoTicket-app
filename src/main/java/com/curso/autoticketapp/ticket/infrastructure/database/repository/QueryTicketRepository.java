package com.curso.autoticketapp.ticket.infrastructure.database.repository;

import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QueryTicketRepository extends JpaRepository<TicketEntity, Long>, JpaSpecificationExecutor<TicketEntity> {

    @NullMarked
    Page<TicketEntity> findAll(Specification<TicketEntity> specification, Pageable pageable);

    Page<TicketEntity> findAllByUserEntity_Id(Long userEntityId, Specification<TicketEntity> specification, Pageable pageable);

    Optional<TicketEntity> findByPublicId(String publicId);

    Optional<TicketEntity> findByPublicIdAndUserEntity_Id(String publicId, Long userEntityId);

    Optional<TicketEntity> findByUuid(UUID uuid);

    List<TicketEntity> uuid(UUID uuid);
}
