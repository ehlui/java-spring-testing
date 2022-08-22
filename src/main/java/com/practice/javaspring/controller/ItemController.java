package com.practice.javaspring.controller;

import com.practice.javaspring.business.ItemBusinessService;
import com.practice.javaspring.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemBusinessService itemBusinessService;

    @GetMapping("/dummy-item")
    public Item getItem() {
        return new Item(1, "Ball", 10, 100);
    }

    @GetMapping("/item-from-business-service")
    public Item itemFromBusinessService() {
        return itemBusinessService.retrievedHardcodedItem();
    }

    @GetMapping("/all-items-from-db")
    public List<Item> getAllItemsFromDatabase() {
        return itemBusinessService.retrieveAllItems();
    }
}
