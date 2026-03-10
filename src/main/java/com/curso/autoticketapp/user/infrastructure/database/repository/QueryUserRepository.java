package com.curso.autoticketapp.user.infrastructure.database.repository;

import com.curso.autoticketapp.user.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QueryUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
