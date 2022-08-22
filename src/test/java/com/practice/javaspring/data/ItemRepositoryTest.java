package com.practice.javaspring.data;

import com.practice.javaspring.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testFindAll() {
        double a = 1;
        int b =(int) a;
        // It will create an in-memory-database, and it'll use the "data.sql" file to populate it
        // As we have 7 new Items to insert we'll have a size of 7 in the test in-memory database
        List<Item> items = itemRepository.findAll();
        assertEquals(7, items.size());


    }
}