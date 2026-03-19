package com.curso.autoticketapp.ticket.infrastructure.database.mapper;

import com.curso.autoticketapp.ticket.domain.entity.TicketAnalysis;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketAnalysisEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TicketAnalysisEntityMapper {

    @Autowired
    public EntityManager entityManager;

    @Mapping(target = "ticket", expression = "java(entityManager.getReference(TicketEntity.class, ticketAnalysis.getTicketId()))")
    public abstract TicketAnalysisEntity mapToTicketAnalysisEntity(TicketAnalysis ticketAnalysis);

    @Mapping(target = "ticketId", source = "ticket.id")
    public abstract TicketAnalysis mapToTicketAnalysis(TicketAnalysisEntity ticketAnalysisEntity);

}
