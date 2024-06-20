package com.inorg.services.product.service;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.LocalizedStringBuilder;
import com.commercetools.api.models.customer.CustomerResourceIdentifierBuilder;
import com.commercetools.api.models.shopping_list.*;
import com.inorg.services.product.com.inorg.services.product.models.ChangeLineItemQuantityData;
import com.inorg.services.product.com.inorg.services.product.models.LineItem;
import com.inorg.services.product.com.inorg.services.product.models.ShoppingListData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShoppingListServiceImpl implements ShoppingListService{

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final ProjectApiRoot apiRoot;

    public ShoppingListServiceImpl(ProjectApiRoot apiRoot) {
        this.apiRoot = apiRoot;
    }


    @Override
    public ShoppingList createShoppingListUsingCustomerKey(ShoppingListData shoppingListData) {
        return apiRoot.shoppingLists()
                .post(ShoppingListDraftBuilder.of()
                     .key(shoppingListData.getKey())
                        .name(LocalizedStringBuilder.of().addValue("en-US",shoppingListData.getName()).build())
                        .slug(LocalizedStringBuilder.of().addValue("en-US",shoppingListData.getSlug()).build())
                        .customer(CustomerResourceIdentifierBuilder.of().key(shoppingListData.getCustomerKey()).build())
                        .deleteDaysAfterLastModification(100L)
                        .build()
                )
                .executeBlocking()
                .getBody();
    }

    @Override
    public ShoppingList addLineItem(String shoppingListId,LineItem lineItem) {
        ShoppingList shoppingList = apiRoot.shoppingLists()
                .withId(shoppingListId)
                .get()
                .executeBlocking()
                .getBody();
        ShoppingListUpdateAction shoppingListUpdateAction = ShoppingListAddLineItemActionBuilder.of()
                .sku(lineItem.getSku())
                .quantity(new Long(lineItem.getQuantity()))
                .build();

        ShoppingListUpdate shoppingListUpdate = ShoppingListUpdate.builder()
                .version(shoppingList.getVersion())
                .actions(shoppingListUpdateAction)
                .build();

        return apiRoot.shoppingLists()
                .withId(shoppingListId)
                .post(shoppingListUpdate)
                .executeBlocking()
                .getBody();

    }

    @Override
    public ShoppingList changeLineItemQuantity(String shoppingListId, ChangeLineItemQuantityData changeLineItemQuantityData) {
        ShoppingList shoppingList = apiRoot.shoppingLists()
                .withId(shoppingListId)
                .get()
                .executeBlocking()
                .getBody();

        ShoppingListUpdateAction shoppingListUpdateAction = ShoppingListChangeLineItemQuantityActionBuilder.of()
                .lineItemId(changeLineItemQuantityData.getLineItemId())
                .quantity(changeLineItemQuantityData.getQuantity())
                .build();

        ShoppingListUpdate shoppingListUpdate = ShoppingListUpdate.builder()
                .version(shoppingList.getVersion())
                .actions(shoppingListUpdateAction)
                .build();

        return apiRoot.shoppingLists()
                .withId(shoppingListId)
                .post(shoppingListUpdate)
                .executeBlocking()
                .getBody();
    }

    @Override
    public ShoppingListPagedQueryResponse getShoppingListOfCustomer(String customerId) {
        return apiRoot.shoppingLists()
                .get()
                .withWhere("customer(id=\""+customerId+"\")")
                .executeBlocking()
                .getBody();
    }
}
