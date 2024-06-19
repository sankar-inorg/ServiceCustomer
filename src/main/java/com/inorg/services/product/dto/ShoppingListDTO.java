package com.inorg.services.product.dto;

import lombok.Data;

@Data
public class ShoppingListDTO {
    private String customerID;
    private String shoppingListName;
    private String shoppingListSlug;
    private String shoppingListKey;
    private String sku;
    private Long quantity;
    /* private String productID;
    private Long variantID; */
}
