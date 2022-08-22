package com.practice.javaspring.business;

import com.practice.javaspring.data.ItemRepository;
import com.practice.javaspring.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/**
 * As business layer class, we only have to test the business logic
 * if we are making Unit testing in here!
 */
@ExtendWith(MockitoExtension.class)
class ItemBusinessServiceTest {

    @Mock
    private ItemRepository repository;

    @InjectMocks
    private ItemBusinessService itemBusinessService;

    @Test
    public void retrieveAllItems_Test() {
        when(repository.findAll())
                .thenReturn(List.of(
                        new Item(2, "ITEM2", 10, 11),
                        new Item(3, "ITEM3", 100, 2)
                ));

        List<Item> items = itemBusinessService.retrieveAllItems();

        // What business logic we want to test: The value param setting value (price*quantity)
        assertEquals(items.get(0).getValue(), 110);
        assertEquals(items.get(1).getValue(), 200);
    }

}