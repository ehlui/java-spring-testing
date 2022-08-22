package com.practice.javaspring.business;

import com.practice.javaspring.data.ItemRepository;
import com.practice.javaspring.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ItemBusinessService {
    @Autowired
    private ItemRepository repository;

    public Item retrievedHardcodedItem() {
        return new Item(1, "Ball", 10, 100);
    }

    public List<Item> retrieveAllItems() {
        List<Item> items = repository.findAll();
        // Adding some business logic to make tests more interesting
        items.forEach(i -> i.setValue(i.getPrice() * i.getQuantity()));
        return repository.findAll();
    }
}
