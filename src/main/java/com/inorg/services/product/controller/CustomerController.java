package com.inorg.services.product.controller;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.inorg.services.product.com.inorg.services.product.models.CustomerData;
import com.inorg.services.product.com.inorg.services.product.models.CustomerGrouper;
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

    @PostMapping(value = "/addCustomerToACustomerGroup", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomerToACustomerGroup(@RequestBody CustomerGrouper CustomerData) {
        LOG.info(CustomerData.toString());
        LOG.info("customer Group and customer {} {}", CustomerData.getGroupID() ,CustomerData.getCustomerID());
        return customerService.AssignCustomertoCustomerGroup(CustomerData.getGroupID(),CustomerData.getCustomerID());
    }

    @GetMapping(value ="CreateEmailVerificationToken/{CustomerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerToken  CreateEmailVerificationToken(@PathVariable String CustomerID){
        LOG.info("Creating Email verification Token for {}",CustomerID);
        return  customerService.CreateEmailVerificationToken(CustomerID);
    }

    @GetMapping(value ="VerifyEmailToken/{EmailToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerToken  EmailVerification(@PathVariable String EmailToken){
        LOG.info("Verification of Token for {}",EmailToken);
        return  customerService.CreateEmailVerificationToken(EmailToken);
    }
}
