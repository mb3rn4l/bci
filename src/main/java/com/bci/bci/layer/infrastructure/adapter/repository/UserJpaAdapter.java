package com.bci.bci.layer.infrastructure.adapter.repository;

import com.bci.bci.layer.domain.exception.NotFoundException;
import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.domain.port.repository.IExistUserPort;
import com.bci.bci.layer.domain.port.repository.IFindUserByTokenPort;
import com.bci.bci.layer.domain.port.repository.ISaveUserPort;
import com.bci.bci.layer.infrastructure.jpa.entity.UserEntity;
import com.bci.bci.layer.infrastructure.jpa.repository.IUserRepository;
import com.bci.bci.layer.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserJpaAdapter implements ISaveUserPort, IFindUserByTokenPort, IExistUserPort {

    private final IUserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public void save(final User user) {

        UserEntity userEntity = userMapper.toEntity(user);

        this.userRepository.save(userEntity);
    }

    @Override
    public User findByToken(final String token) {

        UserEntity userEntity = this.userRepository.findByToken(token)
                .orElseThrow(NotFoundException::new);

        return userMapper.toUser(userEntity);
    }

    @Override
    public boolean exist(final String email) {
        return this.userRepository.existsByEmail(email);
    }


}
