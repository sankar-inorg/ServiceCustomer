package com.inorg.services.product.dto;
import lombok.Data;

@Data
public class UpdateItemQuantityDTO {
    private String shoppingListId;
    private String lineItemId;
    private Long quantity;
}
