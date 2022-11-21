package com.github.wenqiglantz.service.customerservice.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;
}
