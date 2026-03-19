package com.curso.autoticketapp.user.infrastructure.database.repository;

import com.curso.autoticketapp.user.domain.entity.User;
import com.curso.autoticketapp.user.domain.port.UserRepository;
import com.curso.autoticketapp.user.infrastructure.database.entity.UserEntity;
import com.curso.autoticketapp.user.infrastructure.database.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final QueryUserRepository queryUserRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User upsert(User user) {
        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);

        Optional<UserEntity> optionalUser = queryUserRepository.findByEmail(user.getEmail());

        optionalUser.ifPresent(entity -> userEntity.setId(entity.getId()));

        UserEntity userSaved = queryUserRepository.save(userEntity);

        return userEntityMapper.mapToUser(userSaved);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return queryUserRepository.findByEmail(email).map(userEntityMapper::mapToUser);
    }
}
