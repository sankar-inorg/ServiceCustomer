package com.inorg.services.product.com.inorg.services.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGrouper {
    @JsonProperty("CustomerID")
    private String customerID;

    @JsonProperty("GroupID")
    private String groupID;
}
