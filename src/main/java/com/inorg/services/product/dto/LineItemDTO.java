package com.inorg.services.product.dto;

import lombok.Data;

@Data
public class LineItemDTO {
    private String shoppingListID;
    private String productID;
    private Long variantID;
    private Long Quantity;
}
