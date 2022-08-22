package com.practice.javaspring.spike;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Another style of testing using assertj
 * Also comparing it with hamcrest api and considering
 * assertJ more readable (as more "functional")
 */
public class AssertJTest {

    @Test
    public void learningAssertJTest() {
        List<Integer> numbers = Arrays.asList(11, 12, 13);

        // Hamcrest equivalent: assertThat(numbers,hasSize(3), assertThat(x, hasItems)....
        assertThat(numbers).hasSize(3)
                .contains(11, 13)
                .allMatch(x -> x > 10)
                .allMatch(x -> x < 14)
                .noneMatch(x -> x < 0);

        // Working with strings
        assertThat("").isEmpty();
        assertThat("XYZHN")
                .contains("YZ")
                .startsWith("XYZ")
                .endsWith("HN");

        assertThat(numbers.size()).isLessThan(5);
        assertThat(-1).isNotPositive();

        // When importing Condition in AssertJ, look that Condition comes from
        // assertj api and not Hamcrest (because both of them have This class)
        Condition<String> lasMarias = new Condition<>(
                m -> m.contains("dos"), "Son las tres Marias"
        );
        assertThat("Son las tres Marias").doesNotHave(lasMarias);
    }
}
