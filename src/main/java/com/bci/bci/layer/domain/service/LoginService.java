package com.bci.bci.layer.domain.service;


import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.domain.port.repository.IFindUserByEmailPort;
import com.bci.bci.layer.domain.port.repository.ISaveUserPort;
import com.bci.bci.layer.domain.port.service.ILoginService;
import com.bci.bci.layer.domain.utils.IJwt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bci.bci.layer.domain.constants.UserConstants.EMAIL_KEY;

@AllArgsConstructor
@Service
public class LoginService implements ILoginService {

    private final IFindUserByEmailPort userFindByEmailRepository;

    private final ISaveUserPort saveUserRepository;

    private final IJwt jwt;

    @Override
    public User execute(String token) {

        String email = this.jwt.getClaim(token, EMAIL_KEY);
        User user = this.userFindByEmailRepository.findByEmail(email);
        user.updateLastLogin();
        user.updateToken(jwt);

        this.saveUserRepository.save(user);

        return user;
    }
}
