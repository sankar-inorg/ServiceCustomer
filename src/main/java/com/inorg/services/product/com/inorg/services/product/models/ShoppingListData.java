package com.inorg.services.product.com.inorg.services.product.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShoppingListData {
    private String customerKey;;
    private String name;
    private String description;
    private String key;
    private String slug;
}
