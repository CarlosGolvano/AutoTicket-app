package com.curso.autoticketapp.ticket.infrastructure.database.entity;

import com.curso.autoticketapp.ticket.domain.entity.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.TicketStatus;
import com.curso.autoticketapp.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String description;
    private TicketStatus status;

    @Column(nullable = true)
    private TicketPriority priority;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;
}
