package com.bci.bci.layer.domain.port.service;

import com.bci.bci.layer.domain.model.User;

public interface ILoginService {
    User execute(String token);
}
