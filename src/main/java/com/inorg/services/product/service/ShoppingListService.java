package com.inorg.services.product.service;

import com.commercetools.api.models.shopping_list.ShoppingList;
import com.commercetools.api.models.shopping_list.ShoppingListPagedQueryResponse;
import com.inorg.services.product.com.inorg.services.product.models.ChangeLineItemQuantityData;
import com.inorg.services.product.com.inorg.services.product.models.LineItem;
import com.inorg.services.product.com.inorg.services.product.models.ShoppingListData;

public interface ShoppingListService {

    ShoppingList createShoppingListUsingCustomerKey(ShoppingListData shoppingListData);

    ShoppingList addLineItem(String shoppingListId,LineItem shoppingListData);

    ShoppingList changeLineItemQuantity(String shoppingListId, ChangeLineItemQuantityData changeLineItemQuantityData);

    ShoppingListPagedQueryResponse getShoppingListOfCustomer(String customerId);
}
