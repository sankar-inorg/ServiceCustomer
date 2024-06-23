package com.inorg.services.product.controller;


import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.shopping_list.ShoppingList;
import com.commercetools.api.models.shopping_list.ShoppingListPagedQueryResponse;
import com.inorg.services.product.com.inorg.services.product.models.CustomerData;
import com.inorg.services.product.com.inorg.services.product.models.LineItemModel;
import com.inorg.services.product.com.inorg.services.product.models.ShoppingListModel;
import com.inorg.services.product.service.CustomerService;
import com.inorg.services.product.service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping(path = "/shoppinglist", produces = APPLICATION_JSON_VALUE)

public class ShoppingListController {
    private static final Logger LOG = LoggerFactory.getLogger(ShoppingListController.class);
    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(value = "/newshoppinglist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList Newshoppinglist(@RequestBody ShoppingListModel list) {
        LOG.info("Creating new shopping list for customer : {}", list.getCustomerID());
        return shoppingListService.NewShoppingList(list);
    }

    @PostMapping(value = "/additem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList addLineItem(@RequestBody LineItemModel item) {
        LOG.info("Add item to shopping list : {}", item.getListID());
        return shoppingListService.AddItemstoShoppingList(item);
    }

    @PostMapping(value = "/updatequantity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList UpdateItemQuantity(@RequestBody LineItemModel item) {
        LOG.info("Edit quantity of item in shopping list : {}", item.getListID());
        return shoppingListService.UpdateItemQty(item);
    }

    @GetMapping(value = "/getShoppingList/{custid}",produces = APPLICATION_JSON_VALUE)
    public ShoppingListPagedQueryResponse getShoppingList(@PathVariable String custid){
        LOG.info("List of ShoppingLists of customer : {}", custid);
        return shoppingListService.FetchCustomerShoppingLists(custid);
    }
}
