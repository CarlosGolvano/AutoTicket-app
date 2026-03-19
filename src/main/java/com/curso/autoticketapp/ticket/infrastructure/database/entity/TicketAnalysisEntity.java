package com.curso.autoticketapp.ticket.infrastructure.database.entity;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketCategory;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketSentiment;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketUrgency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", unique = true)
    private TicketEntity ticket;

}
