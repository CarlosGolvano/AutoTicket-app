package com.curso.autoticketapp.ticket.infrastructure.database.entity;

import com.curso.autoticketapp.ticket.domain.entity.TicketCategory;
import com.curso.autoticketapp.ticket.domain.entity.TicketSentiment;
import com.curso.autoticketapp.ticket.domain.entity.TicketUrgency;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tickets_analysis")
public class TicketAnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TicketCategory category;
    private TicketSentiment sentiment;
    private TicketUrgency urgency;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", unique = true)
    private TicketEntity ticket;

}
