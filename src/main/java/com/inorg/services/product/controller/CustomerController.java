package com.inorg.services.product.controller;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.commercetools.api.models.customer_group.CustomerGroup;
import com.commercetools.api.models.product.Product;
import com.commercetools.api.models.product.ProductProjection;
import com.commercetools.api.models.product.ProductProjectionPagedQueryResponse;
import com.inorg.services.product.com.inorg.services.product.models.CustomerData;
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

    @PostMapping(value = "/createEmailVerificationToken/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerToken createEmailVerificationToken(@PathVariable String customerId) {
        LOG.info("Create Email verification token : {}", customerId);
        return customerService.createEmailVerificationToken(customerId);
    }

    @GetMapping(value = "/verifyEmailToken/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer verifyEmailToken(@PathVariable String token) {
        LOG.info("Verify  Email verification token : {}", token);
        return customerService.verifyEmailToken(token);
    }

    @PostMapping(value = "/verifyEmail/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer verifyEmail(@PathVariable String token) {
        LOG.info("Verify  Email : {}", token);
        return customerService.verifyEmail(token);
    }

    @PostMapping(value = "/createCustomerGroup/{customerGroupName}/{groupKey}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerGroup createCustomerGroup(@PathVariable String customerGroupName,@PathVariable String groupKey) {
        LOG.info("Create customer Group {}", customerGroupName);
        return customerService.createCustomerGroup(customerGroupName,groupKey);
    }
    @PostMapping(value = "/addCustomerToACustomerGroup/{customerId}/{customerGroupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomerToACustomerGroup(@PathVariable String customerId,@PathVariable String customerGroupId) {
        LOG.info("customer Group and customer {} {}", customerGroupId ,customerId);
        return customerService.addCustomerToACustomerGroup(customerId,customerGroupId);
    }

    //TODO Add more endpoints for Customer
}
