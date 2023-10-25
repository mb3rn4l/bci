package com.bci.bci.layer.infrastructure.mapper;

import com.bci.bci.layer.domain.model.Phone;
import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.infrastructure.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserEntity toEntity(final User user) {
        String phones = user.getPhones()
                .stream()
                .map(phone -> "+" + phone.getCountryCode() + "-" + phone.getCityCode() + "-" + phone.getNumber()
                ).collect(Collectors.joining(";"));

        return UserEntity.builder()
                .created(user.getCreated())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isActive(user.isActive())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(phones)
                .build();
    }

    public User toUser(final UserEntity userEntity) {

        List<Phone> phones = Arrays.stream(userEntity.getPhones().split(";"))
                .map(phone -> {
                    String[] items = phone.split("-");
                    return Phone.builder()
                            .countryCode(items[0])
                            .cityCode(items[1])
                            .number(items[2])
                            .build();
                })
                .collect(Collectors.toList());


        return User.builder()
                .id(userEntity.getId())
                .created(userEntity.getCreated())
                .lastLogin(userEntity.getLastLogin())
                .token(userEntity.getToken())
                .isActive(userEntity.isActive())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .phones(phones)
                .build();

    }
}
