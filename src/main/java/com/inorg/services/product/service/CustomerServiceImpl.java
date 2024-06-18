package com.inorg.services.product.service;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.*;
import com.inorg.services.product.models.CustomerData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final ProjectApiRoot apiRoot;

    public CustomerServiceImpl(ProjectApiRoot apiRoot) {
        this.apiRoot = apiRoot;
    }

    @Override
    public CustomerSignInResult createNewCustomer(CustomerData customerData) {
        CustomerDraft customerDraft = CustomerDraftBuilder.of()
                .email(customerData.getEmail())
                .firstName(customerData.getFirstName())
                .lastName(customerData.getLastName())
                .password(customerData.getPassword())
                .key(customerData.getCustomerKey()) // Optional Attribute
                .build();
        return apiRoot.customers()
                .post(customerDraft)
                .executeBlocking()
                .getBody();
    }

    @Override
    public CustomerSignInResult loginCustomer(CustomerData customerData) {
        return apiRoot.login()
                .post(CustomerSigninBuilder.of()
                        .email(customerData.getEmail())
                        .password(customerData.getPassword())
                        .build())
                .executeBlocking()
                .getBody();
    }

    @Override
    public CustomerToken createResetPasswordToken(String customerId) {
        Customer customer = apiRoot.customers()
                .withId(customerId)
                .get()
                .executeBlocking()
                .getBody();
        return apiRoot.customers()
                .passwordToken()
                .post(CustomerCreatePasswordResetTokenBuilder.of()
                        .email(customer.getEmail())
                        .ttlMinutes(60L)
                        .build())
                .executeBlocking()
                .getBody();
    }

    @Override
    public Customer resetPassword(String token, String newPassword) {
        return apiRoot.customers()
                .passwordReset()
                .post(CustomerResetPasswordBuilder.of()
                        .tokenValue(token)
                        .newPassword(newPassword)
                        .build())
                .executeBlocking()
                .getBody();
    }

    @Override
    public CustomerToken createEmailVerificationToken(String customerId){
        return apiRoot.customers()
                .emailToken()
                .post(CustomerCreateEmailTokenBuilder.of()
                        .id(customerId)
                        .ttlMinutes(60L)
                        .build())
                .executeBlocking()
                .getBody();
    }

    @Override
    public Customer verifyEmailToken(String token) {
        return apiRoot.customers()
                .withEmailToken(token)
                .get()
                .executeBlocking()
                .getBody();
    }

    @Override
    public Customer verifyEmail(String token) {
        return apiRoot.customers()
                .emailConfirm()
                .post(CustomerEmailVerifyBuilder.of()
                        .tokenValue(token).build())
                .executeBlocking()
                .getBody();
    }
}
