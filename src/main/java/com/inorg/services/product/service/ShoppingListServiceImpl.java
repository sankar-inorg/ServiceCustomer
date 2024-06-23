package com.inorg.services.product.service;

import com.commercetools.api.models.common.LocalizedStringBuilder;
import com.commercetools.api.models.customer.CustomerResourceIdentifierBuilder;
import com.commercetools.api.models.shopping_list.*;
import com.inorg.services.product.com.inorg.services.product.models.LineItemModel;
import com.inorg.services.product.com.inorg.services.product.models.ShoppingListModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.commercetools.api.client.ProjectApiRoot;

import java.util.List;


@Service
@Slf4j
public class ShoppingListServiceImpl implements ShoppingListService{

    private static final Logger LOG = LoggerFactory.getLogger(ShoppingListServiceImpl.class);
    private final ProjectApiRoot apiRoot;

    public ShoppingListServiceImpl(ProjectApiRoot apiRoot) {
        this.apiRoot = apiRoot;
    }

    @Override
    public ShoppingList NewShoppingList(ShoppingListModel list) {
        return apiRoot.shoppingLists().post(ShoppingListDraftBuilder.of()
                .key(list.getKey())
                .slug(LocalizedStringBuilder.of().addValue("en-US",list.getSlug()).build())
                .name(LocalizedStringBuilder.of().addValue("en-US",list.getName()).build())
                .customer(CustomerResourceIdentifierBuilder.of().id(list.getCustomerID()).build())
                .deleteDaysAfterLastModification(list.getDdalm()).build())
                .executeBlocking()
                .getBody();
    }

    @Override
    public ShoppingList AddItemstoShoppingList(LineItemModel item) {

        ShoppingList ListtobeUpdated = apiRoot.shoppingLists().withId(item.getListID()).get().executeBlocking().getBody();

        ShoppingListUpdateAction AddItem = ShoppingListAddLineItemActionBuilder.of()
                .productId(item.getProductID())
                .variantId(item.getVariantID())
                .quantity(item.getQuantity())
                .build();

        ShoppingListUpdate Update = ShoppingListUpdateBuilder.of()
                .version(ListtobeUpdated.getVersion())
                .actions(List.of(AddItem))
                .build();

        return apiRoot.shoppingLists()
                .withId(ListtobeUpdated.getId())
                .post(Update)
                .executeBlocking()
                .getBody();
    }

    @Override
    public ShoppingList UpdateItemQty(LineItemModel item) {
        ShoppingList ListtobeUpdated = apiRoot.shoppingLists().withId(item.getListID()).get().executeBlocking().getBody();

        ShoppingListUpdateAction AddItem = ShoppingListChangeLineItemQuantityActionBuilder.of()
                .lineItemId(item.getProductID())
                .quantity(item.getQuantity())
                .build();

        ShoppingListUpdate Update = ShoppingListUpdateBuilder.of()
                .version(ListtobeUpdated.getVersion())
                .actions(List.of(AddItem))
                .build();

        return apiRoot.shoppingLists()
                .withId(ListtobeUpdated.getId())
                .post(Update)
                .executeBlocking()
                .getBody();
    }

    @Override
    public ShoppingListPagedQueryResponse FetchCustomerShoppingLists(String customerId) {
        return apiRoot.shoppingLists()
                .get()
                .withWhere("customer(id=\""+customerId+"\")")
                .executeBlocking()
                .getBody();
    }

}