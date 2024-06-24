package com.inorg.services.product.controller;

import com.commercetools.api.models.shopping_list.ShoppingList;
import com.commercetools.api.models.shopping_list.ShoppingListPagedQueryResponse;
import com.inorg.services.product.dto.LineItemDTO;
import com.inorg.services.product.dto.ShoppingListDTO;
import com.inorg.services.product.dto.UpdateItemQuantityDTO;
import com.inorg.services.product.service.ShoppingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping(path = "/shoppingLists", produces = APPLICATION_JSON_VALUE)
public class ShoppingController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList createShoppingList(@RequestBody ShoppingListDTO shoppingListDTO) {
        LOG.info("Creating a Shopping List");
        return shoppingService.createShoppingList(shoppingListDTO);
    }

    @PostMapping(value = "/addLineItem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList addLineItem(@RequestBody LineItemDTO lineItemDTO) {
        LOG.info("Adding a Line Item to the Shopping List");
        return shoppingService.addLineItem(lineItemDTO);
    }

    @PostMapping(value = "/updateItemQuantity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingList updateItemQuantity(@RequestBody UpdateItemQuantityDTO updateItemQuantityDTO) {
        LOG.info("Updating the Item Quantity of a Line Item in the Shopping List");
        return shoppingService.updateItemQuantity(updateItemQuantityDTO);
    }

    @GetMapping(value="/get/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingListPagedQueryResponse getShoppingListOfCustomer(@PathVariable String customerId) {
        LOG.info("Fetching the Shopping List of a Customer");
        return shoppingService.getShoppingListOfCustomer(customerId);
    }
}
