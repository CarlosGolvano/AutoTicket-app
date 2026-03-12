package com.curso.autoticketapp.ticket.application.command.create;

import com.curso.autoticketapp.common.application.mediator.Request;
import lombok.Data;

@Data
public class CreateTicketRequest implements Request<CreateTicketResponse> {

    private Long user_id;
    private String subject;
    private String description;

}
