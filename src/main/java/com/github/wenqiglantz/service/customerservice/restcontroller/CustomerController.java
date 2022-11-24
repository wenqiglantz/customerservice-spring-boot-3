package com.github.wenqiglantz.service.customerservice.restcontroller;

import com.github.wenqiglantz.service.customerservice.data.CustomerVO;
import com.github.wenqiglantz.service.customerservice.service.CustomerService;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/customers",
        produces = MediaType.APPLICATION_JSON_VALUE)
@ImportRuntimeHints(CustomerController.CustomerControllerRuntimeHints.class)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(@RequestBody CustomerVO customerVO, UriComponentsBuilder uriBuilder)
        throws Exception {
        CustomerVO newCustomerVO = customerService.saveCustomer(customerVO);
        URI location = uriBuilder
                .path("/customers/{customerId}")
                .buildAndExpand(newCustomerVO.getCustomerId())
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(CustomerVO.builder()
                        .customerId(newCustomerVO.getCustomerId())
                        .firstName(newCustomerVO.getFirstName())
                        .lastName(newCustomerVO.getLastName())
                        .build());
    }

    static class CustomerControllerRuntimeHints implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.reflection()
                    .registerConstructor(
                            CustomerService.class.getConstructors()[0], ExecutableMode.INVOKE)
                    .registerMethod(ReflectionUtils.findMethod(
                            CustomerService.class, "saveCustomer", CustomerVO.class), ExecutableMode.INVOKE);
            hints.resources().registerPattern("db/changelog/db.changelog-master.xml");
            hints.resources().registerPattern("db/changelog/db.changelog-1.0.xml");
        }
    }
}
