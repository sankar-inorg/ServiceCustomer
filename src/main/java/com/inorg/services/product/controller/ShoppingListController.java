package com.inorg.services.product.controller;

import com.commercetools.api.models.shopping_list.ShoppingList;
import com.commercetools.api.models.shopping_list.ShoppingListPagedQueryResponse;
import com.inorg.services.product.com.inorg.services.product.models.ChangeLineItemQuantityData;
import com.inorg.services.product.com.inorg.services.product.models.LineItem;
import com.inorg.services.product.com.inorg.services.product.models.ShoppingListData;
import com.inorg.services.product.service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping(path = "/shopping-lists", produces = APPLICATION_JSON_VALUE)
public class ShoppingListController {
    private static final Logger LOG = LoggerFactory.getLogger(ShoppingListController.class);
    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }
    @PostMapping(value="/create", produces = APPLICATION_JSON_VALUE)
    public ShoppingList createShoppingListUsingCustomerKey(@RequestBody ShoppingListData shoppingListData) {
        return shoppingListService.createShoppingListUsingCustomerKey(shoppingListData);
    }
    @PostMapping(value="/{shoppingListId}/addItems", produces = APPLICATION_JSON_VALUE)
    public ShoppingList addLineItem(@PathVariable String shoppingListId,@RequestBody LineItem lineItem) {
        return shoppingListService.addLineItem(shoppingListId,lineItem);
    }
    @PostMapping(value="/{shoppingListId}/changeLineItemQuantity", produces = APPLICATION_JSON_VALUE)
    public ShoppingList changeLineItemQuantity(@PathVariable String shoppingListId,@RequestBody ChangeLineItemQuantityData changeLineItemQuantityData) {
        return shoppingListService.changeLineItemQuantity(shoppingListId,changeLineItemQuantityData);
    }
    @GetMapping(value="/getShoppingListOfCustomer", produces = APPLICATION_JSON_VALUE)
    public ShoppingListPagedQueryResponse getShoppingListOfCustomer(@RequestParam String customerId ) {
        return shoppingListService.getShoppingListOfCustomer(customerId);
    }

}
