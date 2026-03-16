package com.curso.autoticketapp.user.infrastructure.database.entity;

import com.curso.autoticketapp.common.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole role;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Override
    @NonNull
    public String getUsername() {
        return this.email;
    }

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
