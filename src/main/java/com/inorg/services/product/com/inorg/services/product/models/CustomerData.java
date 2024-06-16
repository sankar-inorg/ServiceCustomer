package com.inorg.services.product.com.inorg.services.product.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String customerKey;
}
