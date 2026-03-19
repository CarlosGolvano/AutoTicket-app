package com.curso.autoticketapp.ticket.application.command.create.ticketdetails;

import com.curso.autoticketapp.common.application.mediator.Request;
import com.curso.autoticketapp.common.application.mediator.VoidResponse;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketCategory;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketSentiment;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketUrgency;
import lombok.Data;

@Data
public class CreateTicketAnalysisRequest implements Request<VoidResponse> {

    private Long ticketId;
    private TicketCategory category;
    private TicketSentiment sentiment;
    private TicketUrgency urgency;

}
