package com.inorg.services.product.controller;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.product.Product;
import com.commercetools.api.models.product.ProductProjection;
import com.commercetools.api.models.product.ProductProjectionPagedQueryResponse;
import com.inorg.services.product.com.inorg.services.product.models.CustomerData;
import com.inorg.services.product.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "{customerKey}/custom", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer updateCustomer(@PathVariable String customerKey, @RequestBody CustomerData customerData) {
        LOG.info("Create New Customer with email : {}", customerData.getEmail());
        return customerService.updateCustomerShoeSize(customerKey, customerData.getPreferredShoeSize());
    }

    //TODO Add more endpoints for Customer
}
