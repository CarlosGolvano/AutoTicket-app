package com.curso.autoticketapp.ticket.infrastructure.api.mapper;

import com.curso.autoticketapp.ticket.application.command.create.CreateTicketRequest;
import com.curso.autoticketapp.ticket.application.command.create.CreateTicketResponse;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketDTO;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TicketMapper {

    @Mapping(target = "user_id", ignore = true)
    CreateTicketRequest mapToCreateTicketRequest(CreateTicketDTO createTicketDTO);

    CreateTicketResponseDTO mapToCreateTicketResponseDTO(CreateTicketResponse createTicketResponse);

}
