package com.inorg.services.product.service;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.commercetools.api.models.customer_group.CustomerGroup;
import com.inorg.services.product.com.inorg.services.product.models.CustomerData;

public interface CustomerService {
    public CustomerSignInResult createNewCustomer(CustomerData customerData);

    CustomerSignInResult loginCustomer(CustomerData customerData);

    CustomerToken createRestPasswordToken(String customerId);

    Customer resetPassword(String token, String newPassword);


    CustomerToken createEmailVerificationToken(String customerId);

    Customer verifyEmailToken(String token);

    Customer verifyEmail(String token);

    CustomerGroup createCustomerGroup(String customerGroupName,String groupKey);

    Customer addCustomerToACustomerGroup(String customerId, String customerGroupId);
}
