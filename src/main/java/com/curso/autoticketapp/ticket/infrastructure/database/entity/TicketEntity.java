package com.curso.autoticketapp.ticket.infrastructure.database.entity;

import com.curso.autoticketapp.ticket.domain.entity.enums.TicketPriority;
import com.curso.autoticketapp.ticket.domain.entity.enums.TicketStatus;
import com.curso.autoticketapp.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @Column(name = "public_id", updatable = false, unique = true, nullable = false, length = 5)
    private String publicId;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private TicketStatus status;

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
