package com.github.wenqiglantz.service.customerservice.persistence.repository;

import com.github.wenqiglantz.service.customerservice.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
