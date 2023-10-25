package com.bci.bci.layer.domain.service;


import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.domain.port.repository.IFindUserByTokenPort;
import com.bci.bci.layer.domain.port.repository.ISaveUserPort;
import com.bci.bci.layer.domain.utils.IJwt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginService {

    private final IFindUserByTokenPort userFindByTokenRepository;

    private final ISaveUserPort saveUserRepository;

    private final IJwt jwt;


    public User execute(String token) {

        User user = this.userFindByTokenRepository.findByToken(token);
        user.updateLastLogin();
        user.updateToken(jwt);

        this.saveUserRepository.save(user);

        return user;
    }
}
