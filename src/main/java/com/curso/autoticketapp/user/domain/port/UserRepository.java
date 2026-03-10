package com.curso.autoticketapp.user.domain.port;

import com.curso.autoticketapp.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    User upsert(User user);

    Optional<User> findByEmail(String email);

}
