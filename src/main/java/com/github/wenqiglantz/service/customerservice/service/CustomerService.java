package com.github.wenqiglantz.service.customerservice.service;

import com.github.wenqiglantz.service.customerservice.data.CustomerVO;
import com.github.wenqiglantz.service.customerservice.persistence.entity.Customer;
import com.github.wenqiglantz.service.customerservice.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerVO saveCustomer(CustomerVO customerVO) throws Exception {
        customerVO.setCustomerId(customerVO.getCustomerId() == null ? UUID.randomUUID() : customerVO.getCustomerId());
        Customer customer = Customer.builder()
                .id(customerVO.getCustomerId())
                .firstName(customerVO.getFirstName())
                .lastName(customerVO.getLastName())
                .build();
        customerRepository.save(customer);
        return customerVO;
    }
}
