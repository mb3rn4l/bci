package com.bci.bci.layer.infrastructure.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Phone")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;
}
