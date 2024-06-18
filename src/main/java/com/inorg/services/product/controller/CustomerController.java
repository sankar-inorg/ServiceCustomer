package com.inorg.services.product.controller;

import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.inorg.services.product.models.CustomerData;
import com.inorg.services.product.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping(path = "/customer", produces = APPLICATION_JSON_VALUE)
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerSignInResult createNewCustomer(@RequestBody CustomerData customerData) {
        LOG.info("Create New Customer with email : {}", customerData.getEmail());
        return customerService.createNewCustomer(customerData);
    }

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerSignInResult loginCustomer(@RequestBody CustomerData customerData) {
        LOG.info("Sign In Customer with email : {}", customerData.getEmail());
        return customerService.loginCustomer(customerData);
    }

    @PostMapping(value = "/createResetPasswordToken/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerToken createResetPasswordToken(@PathVariable String customerId) {
        LOG.info("Create Reset Password Token : {}", customerId);
        return customerService.createResetPasswordToken(customerId);
    }

}
