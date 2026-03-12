package com.curso.autoticketapp.ticket.infrastructure.database.mapper;

import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;
import com.curso.autoticketapp.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TicketEntityMapper {

    @Autowired
    public EntityManager entityManager;

    @Mapping(target = "userEntity", expression = "java(entityManager.getReference(UserEntity.class, ticket.getUser_id()))")
    public abstract TicketEntity mapToTicketEntity(Ticket ticket);

    @Mapping(source = "userEntity.id", target = "user_id")
    public abstract Ticket mapToTicket(TicketEntity ticketEntity);

}
