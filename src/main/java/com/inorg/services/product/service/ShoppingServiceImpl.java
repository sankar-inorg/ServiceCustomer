package com.inorg.services.product.service;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.models.common.LocalizedStringBuilder;
import com.commercetools.api.models.customer.CustomerResourceIdentifierBuilder;
import com.commercetools.api.models.shopping_list.ShoppingList;
import com.commercetools.api.models.shopping_list.ShoppingListDraft;
import com.commercetools.api.models.shopping_list.ShoppingListDraftBuilder;
import com.commercetools.api.models.shopping_list.ShoppingListLineItemDraft;
import com.commercetools.api.models.shopping_list.ShoppingListLineItemDraftBuilder;
import com.inorg.services.product.dto.ShoppingListDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
