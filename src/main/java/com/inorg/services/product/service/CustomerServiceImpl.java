package com.inorg.services.product.service;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.customer.*;
import com.commercetools.api.models.customer_group.CustomerGroupResourceIdentifier;
import com.commercetools.api.models.customer_group.CustomerGroupResourceIdentifierBuilder;
import com.inorg.services.product.com.inorg.services.product.models.CustomerData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                .key(customerData.getCustomerKey())
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
    public CustomerToken createRestPasswordToken(String customerId) {
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
    public CustomerToken CreateEmailVerificationToken(String CustomerID) {
        return apiRoot.customers()
                .emailToken()
                .post(CustomerCreateEmailTokenBuilder.of()
                        .id(CustomerID)
                        .ttlMinutes((long) 3)
                        .build())
                .executeBlocking()
                .getBody();
    }

    @Override
    public Customer VerifyEmailToken(String Token) {
        Customer temp = apiRoot.customers()
                .withEmailToken(Token)
                .get()
                .executeBlocking()
                .getBody();
        if (temp!=null)
            return temp;
        LOG.info("Customer with token not found");
        return null;
    }

    @Override
    public Customer AssignCustomertoCustomerGroup(String GroupID, String CustomerID) {
        List<CustomerUpdateAction> UpdateActions = new ArrayList<>();

        CustomerUpdateAction AssignCustomertoCustomerGroup = CustomerSetCustomerGroupActionBuilder.of()
                .customerGroup(CustomerGroupResourceIdentifierBuilder.of()
                                .id(GroupID)
                                .build()
                        ).build();
        UpdateActions.add(AssignCustomertoCustomerGroup);

        Customer CustomerToBeUpdated = apiRoot.customers()
                .withId(CustomerID)
                .get()
                .executeBlocking()
                .getBody();

        CustomerUpdate Updates = CustomerUpdateBuilder.of()
                .version(CustomerToBeUpdated.getVersion())
                .actions(UpdateActions)
                .build();

        return apiRoot.customers()
                .withId(CustomerID)
                .post(Updates)
                .executeBlocking()
                .getBody();

    }


}
