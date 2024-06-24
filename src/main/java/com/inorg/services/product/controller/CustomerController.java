package com.inorg.services.product.controller;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.commercetools.api.models.customer_group.CustomerGroup;
import com.inorg.services.product.dto.ResetPasswordDTO;
import com.inorg.services.product.dto.UpdateCustomerDTO;
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

    public CustomerController(CustomerService customerService) { // Constructor Injection
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

    @PostMapping(value = "/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer createResetPasswordToken(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        LOG.info("Resetting the Password");
        return customerService.resetPassword(resetPasswordDTO.getToken(), resetPasswordDTO.getNewPassword());
    }

    @PostMapping(value = "/createEmailVerificationToken/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerToken createEmailVerificationToken(@PathVariable String customerId) {
        LOG.info("Create Email Verification Token : {}", customerId);
        return customerService.createEmailVerificationToken(customerId);
    }

    @GetMapping(value = "/verifyEmailToken/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer verifyEmailToken(@PathVariable String token) {
        LOG.info("Verifying the Email Token : {}", token);
        return customerService.verifyEmailToken(token);
    }

    @PostMapping(value = "/verifyEmail/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer verifyEmail(@PathVariable String token) {
        LOG.info("Verify Email : {}", token);
        return customerService.verifyEmail(token);
    }

    @PostMapping(value = "/createCustomerGroup/{customerGroupName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerGroup createCustomerGroup(@PathVariable String customerGroupName) {
        LOG.info("Create Customer Group {}", customerGroupName);
        return customerService.createCustomerGroup(customerGroupName);
    }

    @PostMapping(value = "/addCustomerToACustomerGroup/{customerId}/{customerGroupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomerToACustomerGroup(@PathVariable String customerId, @PathVariable String customerGroupId) {
        LOG.info("Adding Customer to a Customer Group {} {}", customerId, customerGroupId);
        return customerService.addCustomerToACustomerGroup(customerId,customerGroupId);
    }

    @PostMapping(value = "updateCustomer/{customerKey}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer updateCustomer(@PathVariable String customerKey, @RequestBody UpdateCustomerDTO updateCustomerDTO) {
        LOG.info("Updating the Customer Details");
        return customerService.updateCustomerShoeSize(customerKey, updateCustomerDTO.getPreferredShoeSize());
    }

    @GetMapping(value = "/query-by/{preferredShoeSize}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerPagedQueryResponse getCustomerByShoeSize(@PathVariable String preferredShoeSize) {
        LOG.info("Get Customer by Shoe Size : {}", preferredShoeSize);
        return customerService.getCustomerByShoeSize(preferredShoeSize);
    }

}