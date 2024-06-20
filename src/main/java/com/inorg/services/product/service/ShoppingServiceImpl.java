package com.inorg.services.product.service;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.LocalizedStringBuilder;
import com.commercetools.api.models.customer.CustomerResourceIdentifierBuilder;
import com.commercetools.api.models.shopping_list.*;
import com.inorg.services.product.dto.LineItemDTO;
import com.inorg.services.product.dto.ShoppingListDTO;
import com.inorg.services.product.dto.UpdateItemQuantityDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShoppingServiceImpl implements ShoppingService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final ProjectApiRoot apiRoot;

    public ShoppingServiceImpl(ProjectApiRoot apiRoot) {
        this.apiRoot = apiRoot;
    }

    public ShoppingList createShoppingList(ShoppingListDTO shoppingListDTO) {
        ShoppingListLineItemDraft shoppingListLineItemDraft = ShoppingListLineItemDraftBuilder.of()
                .sku(shoppingListDTO.getSku())
                .quantity(shoppingListDTO.getQuantity())
                .build();
        // Create ShoppingList Draft - Set ShoppingList Data
        ShoppingListDraft shoppingListDraft = ShoppingListDraftBuilder.of()
                .name(LocalizedStringBuilder.of()
                        .addValue("en", shoppingListDTO.getShoppingListName())
                        .build())
                .slug(LocalizedStringBuilder.of()
                        .addValue("en", shoppingListDTO.getShoppingListSlug())
                        .build())
                .customer(CustomerResourceIdentifierBuilder.of()
                        .id(shoppingListDTO.getCustomerID())
                        .build())
                .key(shoppingListDTO.getShoppingListKey())
                .lineItems(shoppingListLineItemDraft)
                .build();

        return apiRoot.shoppingLists()
                .post(shoppingListDraft)
                .executeBlocking()
                .getBody();
    }

    public ShoppingList addLineItem(LineItemDTO lineItemDTO) {
        // Get the ShoppingList
        ShoppingList shoppingList = apiRoot.shoppingLists()
                .withId(lineItemDTO.getShoppingListID())
                .get()
                .executeBlocking()
                .getBody();
        // Create and Update the ActionList
        List<ShoppingListUpdateAction> shoppingListUpdateActionList = new ArrayList<>();
        ShoppingListUpdateAction shoppingListUpdateAction = ShoppingListAddLineItemActionBuilder.of()
                .productId(lineItemDTO.getProductID())
                .variantId(lineItemDTO.getVariantID())
                .quantity(lineItemDTO.getQuantity())
                .build();
        shoppingListUpdateActionList.add(shoppingListUpdateAction);
        // Create an Update Object
        ShoppingListUpdate shoppingListUpdate = ShoppingListUpdateBuilder.of()
                .version(shoppingList.getVersion())
                .actions(shoppingListUpdateActionList)
                .build();
        // Make the API Call by passing the Object
        return apiRoot.shoppingLists()
                .withId(lineItemDTO.getShoppingListID())
                .post(shoppingListUpdate)
                .executeBlocking()
                .getBody();
    }

    public ShoppingList updateItemQuantity(UpdateItemQuantityDTO updateItemQuantityDTO) {
        // Get the ShoppingList
        ShoppingList shoppingList = apiRoot.shoppingLists()
                .withId(updateItemQuantityDTO.getShoppingListId())
                .get()
                .executeBlocking()
                .getBody();
        List<ShoppingListChangeLineItemQuantityAction> shoppingListChangeLineItemQuantityActionList = new ArrayList<>();
        ShoppingListChangeLineItemQuantityAction shoppingListChangeLineItemQuantityAction = ShoppingListChangeLineItemQuantityActionBuilder.of()
                .lineItemId(updateItemQuantityDTO.getLineItemId())
                .quantity(updateItemQuantityDTO.getQuantity())
                .build();
        shoppingListChangeLineItemQuantityActionList.add(shoppingListChangeLineItemQuantityAction);

        // Create an Update Object
        ShoppingListUpdate shoppingListUpdate = ShoppingListUpdateBuilder.of()
                .actions((ShoppingListUpdateAction) shoppingListChangeLineItemQuantityActionList)
                .build();
        // Make the API Call by passing the Object
        return apiRoot.shoppingLists()
                .withId(updateItemQuantityDTO.getShoppingListId())
                .post(shoppingListUpdate)
                .executeBlocking()
                .getBody();
    }
}
