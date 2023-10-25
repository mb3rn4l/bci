package com.bci.bci.layer.infrastructure.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate created;

    private LocalDate lastLogin;

    private String token;

    private boolean isActive;

    private String name;

    private String email;

    private String password;

    private String phones;
}
