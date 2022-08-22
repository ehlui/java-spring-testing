package com.practice.javaspring.spike;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

// Remember using all same from the same API (hamcrest in this case)
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersTest {

    @Test
    public void learningHamcrest() {
        List<Integer> numbers = Arrays.asList(11, 12, 13);
        // See how readable is Hamcrest test api (statics are cool)
        assertThat(numbers,hasSize(3));
        assertThat(numbers,hasItems(11,12));
        assertThat(numbers,everyItem(greaterThan(10)));

        assertThat("",blankString());
        assertThat("ABCSE",containsString("CS"));
        assertThat("ABCSE",startsWith("ABC"));
        assertThat("ABCSE",endsWith("SE"));
    }
}
