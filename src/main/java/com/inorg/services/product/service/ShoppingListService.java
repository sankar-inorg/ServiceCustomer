package com.inorg.services.product.service;

import com.commercetools.api.models.shopping_list.ShoppingList;
import com.commercetools.api.models.shopping_list.ShoppingListPagedQueryResponse;
import com.inorg.services.product.com.inorg.services.product.models.LineItemModel;
import com.inorg.services.product.com.inorg.services.product.models.ShoppingListModel;

import java.util.List;

public interface ShoppingListService {
    ShoppingList NewShoppingList(ShoppingListModel list);
    ShoppingList AddItemstoShoppingList(LineItemModel item);
    ShoppingList UpdateItemQty(LineItemModel item);
    ShoppingListPagedQueryResponse FetchCustomerShoppingLists(String customerId);
}
