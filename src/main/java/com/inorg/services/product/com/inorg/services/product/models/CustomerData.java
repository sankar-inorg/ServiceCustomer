package com.inorg.services.product.com.inorg.services.product.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerData {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String customerKey;
}
