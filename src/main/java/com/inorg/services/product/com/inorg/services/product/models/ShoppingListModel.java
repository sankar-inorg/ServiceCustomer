package com.inorg.services.product.com.inorg.services.product.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingListModel {
    private String name;
    private String slug;
    private String customerID;
    private String key;
    private Long ddalm;
}
