package com.bci.bci.layer.domain.service;

import com.bci.bci.layer.application.dto.CreateUserRequest;
import com.bci.bci.layer.domain.exception.UserAlreadyExistException;
import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.domain.port.repository.IExistUserPort;
import com.bci.bci.layer.domain.port.repository.ISaveUserPort;
import com.bci.bci.layer.domain.port.service.ISignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bci.bci.layer.domain.constants.UserConstants.*;
import static com.bci.bci.layer.domain.constants.UserConstants.INVALID_EMAIL_FORMAT;
import static com.bci.bci.layer.domain.validation.ArgumentValidator.validateRegex;
import static com.bci.bci.layer.domain.validation.ArgumentValidator.validateRequired;

@RequiredArgsConstructor
@Service
public class SignUpService implements ISignUpService {

    private final ISaveUserPort saveUserRepository;

    private final IExistUserPort existUserRepository;

    @Override
    public void execute(User user) {

        validateUSer(user);

        if (this.existUserRepository.exist(user.getEmail())) {
            throw new UserAlreadyExistException();
        }

        this.saveUserRepository.save(user);
    }

    private void validateUSer(User user) {
        validateRequired(user.getEmail(), EMAIL_REQUIRED);
        validateRequired(user.getPassword(), PASSWORD_REQUIRED);

        // validate one upper case and length with regex
        validateRegex(user.getPassword(), PASSWORD_LENGTH_AND_ONE_UPPERCASE_REGEX, INVALID_PASSWORD_FORMAT);

        // validate two numbers not in a row
        validateRegex(user.getPassword(), PASSWORD_TWO_NUMBERS_REGEX, INVALID_PASSWORD_FORMAT);

        // validate email
        validateRegex(user.getEmail(), EMAIL_REGEX, INVALID_EMAIL_FORMAT);
    }
}
