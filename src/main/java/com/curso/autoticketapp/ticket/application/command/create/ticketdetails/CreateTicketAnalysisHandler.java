package com.curso.autoticketapp.ticket.application.command.create.ticketdetails;

import com.curso.autoticketapp.common.application.mediator.RequestHandler;
import com.curso.autoticketapp.common.application.mediator.VoidResponse;
import com.curso.autoticketapp.ticket.domain.entity.Ticket;
import com.curso.autoticketapp.ticket.domain.entity.TicketAnalysis;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.exceptions.TicketNotFoundException;
import com.curso.autoticketapp.ticket.domain.port.TicketAnalysisRepository;
import com.curso.autoticketapp.ticket.domain.port.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateTicketAnalysisHandler implements RequestHandler<CreateTicketAnalysisRequest, VoidResponse> {

    private final TicketAnalysisRepository ticketAnalysisRepository;

    private final TicketRepository ticketRepository;

    @Override
    public VoidResponse handle(CreateTicketAnalysisRequest request) {

        Optional<Ticket> optionalTicket = ticketRepository.findById(request.getTicketId());

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            TicketAnalysis ticketAnalysis = TicketAnalysis.builder()
                    .ticketId(request.getTicketId())
                    .category(request.getCategory())
                    .sentiment(request.getSentiment())
                    .urgency(request.getUrgency())
                    .build();

            ticketAnalysisRepository.upsert(ticketAnalysis);

            TicketPriority priority = PriorityCalculator.calculateTicketPriority(request.getSentiment(), request.getUrgency());
            ticket.setPriority(priority);

            ticketRepository.upsert(ticket);
        } else {
            log.error("Ticket {} not found when processing analysis. Skipping.", request.getTicketId());
            throw new TicketNotFoundException(request.getTicketId());
        }

        return new VoidResponse();
    }

    @Override
    public Class<CreateTicketAnalysisRequest> getRequestType() {
        return CreateTicketAnalysisRequest.class;
    }
}
