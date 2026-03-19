package com.curso.autoticketapp.ticket.application.command.create.ticket;

import com.curso.autoticketapp.common.application.mediator.Request;
import lombok.Data;

@Data
public class CreateTicketRequest implements Request<CreateTicketResponse> {

    private Long userId;
    private String subject;
    private String description;

}
