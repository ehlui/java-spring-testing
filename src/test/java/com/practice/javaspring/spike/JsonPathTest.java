package com.practice.javaspring.spike;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class JsonPathTest {

    @Test
    public void learning() {
        String responseFromService = "[" +
                "{\"id\":1,\"name\":\"Fanta 2L\",\"quantity\":5}," +
                "{\"id\":2,\"name\":\"Cola 2L\",\"quantity\":2}," +
                "{\"id\":3,\"name\":\"Nestea 2L\",\"quantity\":100}," +
                "{\"id\":4,\"name\":\"Agua 1.5L\",\"quantity\":100}" +
                "]";
        // JsonPath => Allow us to get data from JSON very easily (Easily mapping)

        //Parsing a json
        DocumentContext context = JsonPath.parse(responseFromService);
        // Getting the number of elements = total
        int length = context.read("$.length()");
        assertThat(length).isEqualTo(4);

        // Getting all the ids
        List<Integer> idList = context.read("$..id"); // $.. -> 1st level (outter array) 2nd dot level (inside object)
        assertThat(idList).containsExactly(1, 2, 3, 4);

        // Specific searches
        System.out.println(context.read("$.[1]").toString());
        System.out.println(context.read("$.[0:2]").toString()); // First 2 elements (0->n-1)
        // Find a property and if it matches print the whole object/objects with that field value
        System.out.println(context.read("$.[?(@.name=='Nestea 2L')]").toString());
        System.out.println(context.read("$.[?(@.quantity=='100')]").toString());

        // Getting all the names
        List<String> nameList = context.read("$..name");
        assertThat(nameList).contains("Fanta 2L", "Cola 2L");
    }

    /*
    Another example but in the controller layer and mocking a post request

        @ActiveProfiles("test")
      // Source:https://mkyong.com/spring-boot/spring-test-how-to-test-a-json-array-in-jsonpath/
        {
            "timestamp":"2019-03-05T09:34:13.280+0000",
            "status":400,
            "errors":["Author is not allowed.","Please provide a price","Please provide a author"]
        }

    @Test
    public void save_emptyAuthor_emptyPrice_400() throws Exception {

        String bookInJson = "{\"name\":\"ABC\"}";

        mockMvc.perform(post("/books")
                        .content(bookInJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(3)))
                .andExpect(jsonPath("$.errors", hasItem("Author is not allowed.")))
                .andExpect(jsonPath("$.errors", hasItem("Please provide a author")))
                .andExpect(jsonPath("$.errors", hasItem("Please provide a price")));

        verify(mockRepository, times(0)).save(any(Book.class));

    }


    * */
}
