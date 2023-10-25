package com.bci.bci.layer.domain.service;

import com.bci.bci.layer.domain.exception.UserAlreadyExistException;
import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.domain.port.repository.IExistUserPort;
import com.bci.bci.layer.domain.port.repository.ISaveUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpService {

    private final ISaveUserPort saveUserRepository;

    private final IExistUserPort existUserRepository;

    public void execute(User user) {

        if (this.existUserRepository.exist(user.getEmail())) {
            throw new UserAlreadyExistException();
        }

        this.saveUserRepository.save(user);
    }
}
