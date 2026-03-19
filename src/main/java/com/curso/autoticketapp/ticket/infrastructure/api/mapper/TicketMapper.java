package com.curso.autoticketapp.ticket.infrastructure.api.mapper;

import com.curso.autoticketapp.common.domain.pagination.PaginationResult;
import com.curso.autoticketapp.ticket.application.command.create.ticket.CreateTicketRequest;
import com.curso.autoticketapp.ticket.application.command.create.ticket.CreateTicketResponse;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketDTO;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.CreateTicketResponseDTO;
import com.curso.autoticketapp.ticket.infrastructure.api.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TicketMapper {

    @Mapping(target = "userId", ignore = true)
    CreateTicketRequest mapToCreateTicketRequest(CreateTicketDTO createTicketDTO);

    CreateTicketResponseDTO mapToCreateTicketResponseDTO(CreateTicketResponse createTicketResponse);

    PaginationResult<TicketDTO> mapToPaginationTicketDTO(PaginationResult<Ticket> tickets);

    TicketDTO mapToTicketDTO(Ticket ticket);

}
