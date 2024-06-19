package com.inorg.services.product.service;

import com.commercetools.api.models.shopping_list.ShoppingList;
import com.inorg.services.product.dto.ShoppingListDTO;

public interface ShoppingService {

    ShoppingList createShoppingList(ShoppingListDTO shoppingListDTO);
}
