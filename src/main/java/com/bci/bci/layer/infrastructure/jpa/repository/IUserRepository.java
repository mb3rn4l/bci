package com.bci.bci.layer.infrastructure.jpa.repository;

import com.bci.bci.layer.infrastructure.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String token);

    boolean existsByEmail(String email);
}
