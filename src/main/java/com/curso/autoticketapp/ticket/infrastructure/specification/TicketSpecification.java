package com.curso.autoticketapp.ticket.infrastructure.specification;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketStatus;
import com.curso.autoticketapp.ticket.infrastructure.database.entity.TicketEntity;
import org.springframework.data.jpa.domain.Specification;

public class TicketSpecification {

    public static Specification<TicketEntity> byStatus(TicketStatus status) {
        return (root, query, cb) -> status == null ? null : cb.like(root.get("status"), "%" + status + "%");
    }

    public static Specification<TicketEntity> byPriority(TicketPriority priority) {
        return (root, query, cb) -> priority == null ? null : cb.like(root.get("priority"), "%" + priority + "%");
    }

}

