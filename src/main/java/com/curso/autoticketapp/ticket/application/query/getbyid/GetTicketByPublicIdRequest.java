package com.curso.autoticketapp.ticket.application.query.getbyid;

import com.curso.autoticketapp.common.application.mediator.Request;
import com.curso.autoticketapp.common.domain.enums.UserRole;

import java.util.List;

public record GetTicketByPublicIdRequest(
        String publicId,
        Long userId,
        List<UserRole> roles
) implements Request<GetTicketByPublicIdResponse> {}
