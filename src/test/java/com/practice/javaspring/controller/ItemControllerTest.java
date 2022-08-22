package com.practice.javaspring.controller;

import com.practice.javaspring.business.ItemBusinessService;
import com.practice.javaspring.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean // We create a "component"/"bean" object mocked to be used in advance
    private ItemBusinessService itemBusinessServiceMock;

    @Test
    public void dummy_BasicTest() throws Exception {
        // call endpoint /hello-world
        // verify "Hello World" response
        // 1. We first need to build the request content
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);
        // 2. Perform the request/sent it (we're actually mocking this behaviour)
        mockMvc.perform(request)
                .andExpect(status().isOk())
                // Escaped values are useful only for strings with spaces or with strict mode on
                .andExpect(content().json("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}", false))
                .andReturn();
        // When response is simple we don't need to assert because is already been tested with "andExpect"
        //assertEquals("Hello World", result.getResponse().getContentAsString());

        // When calling .json() in "andExpect" the actual response is from the mockMvc requests (the response mocked)
        //JSONAssert.assertEquals(expected,actual,isStrict);
    }

    @Test
    public void itemFromBusinessService_BasicTest() throws Exception {
        // We need to specify our mocking class is using our MockBean service
        when(itemBusinessServiceMock.retrievedHardcodedItem())
                .thenReturn(new Item(2, "ITEM 2", 10, 11));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                // Escaped values are useful only for strings with spaces or with strict mode on
                .andExpect(content().json("{id:2,name:\"ITEM 2\",price:10,quantity:11}"))
                .andReturn();
    }

    @Test
    public void getAllItems_BasicTest() throws Exception {
        // Unit test -> we don't want to talk to business service but stub it to test our class without dependencies

        // GIVEN (our stubbed service - dependency)
        when(itemBusinessServiceMock.retrieveAllItems())
                .thenReturn(List.of(
                        new Item(2, "ITEM2", 10, 11),
                        new Item(3, "ITEM3", 1213, 2)
                ));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-db")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                // Escaped values are useful only for strings with spaces or with strict mode on
                .andExpect(content().json("[{id:2,name:ITEM2,price:10,quantity:11},{id:3,name:ITEM3,price:1213,quantity:2}]"))
                .andReturn();
    }

}