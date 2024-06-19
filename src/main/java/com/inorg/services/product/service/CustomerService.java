package com.inorg.services.product.service;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer.CustomerToken;
import com.inorg.services.product.com.inorg.services.product.models.CustomerData;

public interface CustomerService {
    public CustomerSignInResult createNewCustomer(CustomerData customerData);

    CustomerSignInResult loginCustomer(CustomerData customerData);

    CustomerToken createRestPasswordToken(String customerId);

    Customer resetPassword(String token, String newPassword);

    Customer updateCustomerShoeSize(String customerKey, String shoeSize);


}
