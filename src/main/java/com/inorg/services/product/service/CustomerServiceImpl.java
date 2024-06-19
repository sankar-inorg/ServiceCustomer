package com.inorg.services.product.service;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.LocalizedStringBuilder;
import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerCreatePasswordResetTokenBuilder;
import com.commercetools.api.models.customer.CustomerDraft;
import com.commercetools.api.models.customer.CustomerDraftBuilder;
import com.commercetools.api.models.customer.CustomerPagedQueryResponse;
import com.commercetools.api.models.customer.CustomerResetPasswordBuilder;
import com.commercetools.api.models.customer.CustomerSetCustomTypeActionBuilder;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerSigninBuilder;
import com.commercetools.api.models.customer.CustomerToken;
import com.commercetools.api.models.customer.CustomerUpdateAction;
import com.commercetools.api.models.customer.CustomerUpdateBuilder;
import com.commercetools.api.models.type.FieldContainerBuilder;
import com.commercetools.api.models.type.TypeResourceIdentifierBuilder;
import com.inorg.services.product.com.inorg.services.product.models.CustomerData;
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
    public Customer updateCustomerShoeSize(String customerKey, String shoeSize) {
        Customer customer = apiRoot.customers()
                .withKey(customerKey)
                .get()
                .executeBlocking()
                .getBody();

        CustomerUpdateAction updateAction = CustomerSetCustomTypeActionBuilder.of()
                .type(TypeResourceIdentifierBuilder.of()
                        .key("customer-preferredShoeSize")
                        .build())
                .fields(FieldContainerBuilder.of()
                            .addValue("preferredShoeSize", LocalizedStringBuilder.of()
                                    .addValue("en-US", shoeSize).build())
                            .build())
                .build();


        return apiRoot.customers()
                .withId(customer.getId())
                .post(CustomerUpdateBuilder.of()
                        .version(customer.getVersion())
                        .actions(updateAction)
                        .build())
                .executeBlocking()
                .getBody();
    }

    @Override
    public CustomerPagedQueryResponse getCustomerByShoeSize(String preferredShoeSize) {
        return apiRoot.customers()
                .get()
                .withWhere("custom(fields(preferredShoeSize(en-US = \"" + preferredShoeSize + "\")))")
                .executeBlocking()
                .getBody();
    }
}
