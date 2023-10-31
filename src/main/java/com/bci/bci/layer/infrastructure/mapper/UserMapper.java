package com.bci.bci.layer.infrastructure.mapper;

import com.bci.bci.layer.domain.model.Phone;
import com.bci.bci.layer.domain.model.User;
import com.bci.bci.layer.infrastructure.jpa.entity.PhoneEntity;
import com.bci.bci.layer.infrastructure.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserEntity toEntity(final User user) {

        List<PhoneEntity> phones = user.getPhones().stream()
                .map(phone -> PhoneEntity.builder()
                        .countryCode(phone.getCountryCode())
                        .cityCode(phone.getCityCode())
                        .number(phone.getNumber())
                        .build()
                )
                .collect(Collectors.toList());

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

        List<Phone> phones = userEntity.getPhones().stream()
                .map(phoneEntity -> Phone.builder()
                        .id(phoneEntity.getId())
                        .countryCode(phoneEntity.getCountryCode())
                        .cityCode(phoneEntity.getCityCode())
                        .number(phoneEntity.getNumber())
                        .build()
                ).collect(Collectors.toList());

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
