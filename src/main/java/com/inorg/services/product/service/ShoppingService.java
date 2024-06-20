package com.inorg.services.product.service;

import com.commercetools.api.models.shopping_list.ShoppingList;
import com.inorg.services.product.dto.LineItemDTO;
import com.inorg.services.product.dto.ShoppingListDTO;
import com.inorg.services.product.dto.UpdateItemQuantityDTO;

public interface ShoppingService {

    ShoppingList createShoppingList(ShoppingListDTO shoppingListDTO);

    ShoppingList addLineItem(LineItemDTO lineItemDTO);

    ShoppingList updateItemQuantity(UpdateItemQuantityDTO updateItemQuantityDTO);
}
