package com.bci.bci.layer.domain.port.repository;

import com.bci.bci.layer.domain.model.User;

public interface IFindUserByEmailPort {

    User findByEmail(String email);
}
