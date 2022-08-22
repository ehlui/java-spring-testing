package com.practice.javaspring.controller;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

// Powerful annotation:
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Rnd are useful for CI servers avoiding clashes
// @SpringBootTest -> finds recursively where is @SpringBootApplication
// 1. It will go from the current to backwards --> package com.practice.junit5test(2).controller(1);
        //2. Then it will load the context of that class with the found @SpringBootApplication
        // - This will launch each component from our application (like normal running)
        // - Data will be stored into in-memory database
class ItemControllerTestIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate; // Enable us to make calls, It will use our random environment port config set at @SpringBootTest
    // This only will the specific endpoint and it will be ready!


    /*@MockBean  --> If we have an external DB and just want to test our APP (current spring app) we just need to mock the db dependency and set which behaviour we need for our ii test
    private ItemRepository repository;*/

    @Test
    public void contextLoads() throws Exception {
        // Return type -- selected from endpoint
        String response = this.testRestTemplate.getForObject("/all-items-from-db", String.class);

        JSONAssert.assertEquals("[{id:10001},{id:10002},{id:10003},{id:10004},{id:10005},{id:10006},{id:10007}]", response, false);
    }


}




