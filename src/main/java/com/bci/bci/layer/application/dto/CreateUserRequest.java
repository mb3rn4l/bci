package com.bci.bci.layer.application.dto;

import com.bci.bci.layer.domain.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String name;

    private String email;

    private String password;

    private List<Phone> phones;

}
