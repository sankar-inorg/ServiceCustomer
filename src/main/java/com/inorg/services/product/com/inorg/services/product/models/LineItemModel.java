package com.inorg.services.product.com.inorg.services.product.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemModel {
    private String listID;
    private String productID;
    private Long variantID;
    private Long Quantity;
}
