package com.github.wenqiglantz.service.customerservice.service;

import com.github.wenqiglantz.service.customerservice.data.CustomerVO;
import com.github.wenqiglantz.service.customerservice.data.exception.NotFoundException;
import com.github.wenqiglantz.service.customerservice.persistence.entity.Customer;
import com.github.wenqiglantz.service.customerservice.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

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

    public CustomerVO getCustomer(String customerId) {
        Customer customer =
                customerRepository.findById(UUID.fromString(customerId)).orElseThrow(() ->
                        new NotFoundException("Could not find customer with customerId: " + customerId));

        CustomerVO customerVO = CustomerVO.builder()
                .customerId(UUID.fromString(customerId))
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
        return customerVO;
    }

    public List<CustomerVO> getCustomers() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerVO> customerVOS = customers.stream()
                .map(customer -> CustomerVO.builder()
                        .customerId(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .build())
                .collect(toList());

        return customerVOS;
    }

    public void updateCustomer(String customerId, CustomerVO customerVO) throws Exception {
        Customer customer =
                customerRepository.findById(UUID.fromString(customerId)).orElseThrow(() ->
                        new NotFoundException("Could not find customer with customerId: " + customerId));
        customer.setFirstName(customerVO.getFirstName());
        customer.setLastName(customerVO.getLastName());
        customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId) throws Exception {
        Customer customer =
                customerRepository.findById(UUID.fromString(customerId)).orElseThrow(() ->
                        new NotFoundException("Could not find customer with customerId: " + customerId));
        customerRepository.delete(customer);
    }
}
