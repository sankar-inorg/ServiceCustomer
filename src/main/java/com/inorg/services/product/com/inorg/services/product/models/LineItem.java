package com.inorg.services.product.com.inorg.services.product.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LineItem {
    private String sku;
    private Integer quantity;
}
