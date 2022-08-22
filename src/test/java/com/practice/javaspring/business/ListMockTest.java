package com.practice.javaspring.business;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTest {

    List<String> mock = mock(List.class);


    @Test
    public void basic_size_test() {
        when(mock.size()).thenReturn(5);
        assertEquals(5, mock.size());
    }

    @Test
    public void returnDifferentValuesTest() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5, mock.size());
        assertEquals(10, mock.size());
    }

    @Test
    public void returnWithParametersTest() {
        when(mock.get(0)).thenReturn("in just 5 min");
        assertEquals("in just 5 min", mock.get(0));
    }

    @Test// Using argument Matcher --> Generics (ex:anyInt())
    public void returnWithGenericParamsTest() {
        when(mock.get(anyInt())).thenReturn("in just 5 min");
        assertEquals("in just 5 min", mock.get(0));
    }

    @Test
    public void verificationBasics() {
        // SUT == SYSTEM UNDER TEST
        String value = mock.get(0);
        String value2 = mock.get(1);


        verify(mock).get(0);
        //verify(mock).get(anyInt()) --> in this test is called more than 1, by default verify has times(1)
        verify(mock, times(2)).get(anyInt());
        verify(mock, atLeast(1)).get(anyInt()); // ==verify(mock, atLeastOnce()).get(anyInt());
        verify(mock, atMost(2)).get(anyInt()); // at most == as max amount
        verify(mock, never()).get(2);

    }

    @Test
    public void argumentCapturing() {
        // SUT
        mock.add("SomeString");

        // Verification - verifying the kind of object passed to add() method
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture()); // Here captor object "captures" the argument passed

        // After capturing the value passed we can try to assert if it's the same string
        assertEquals("SomeString", captor.getValue());
    }

    @Test
    public void multipleArgumentCapturing() {
        //What we want:
        // capture all the values passed to add() method

        // SUT
        mock.add("SomeString1");
        mock.add("SomeString2");

        // Verification - verifying the kind of object passed to add() method
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock, times(2)).add(captor.capture()); // Here captor object "captures" the argument passed twice!! (default is 1!!!)

        // After capturing the value passed we can try to assert if it's the same string
        // now we get all the values
        List<String> allValues = captor.getAllValues();
        assertEquals("SomeString1", allValues.get(0));
        assertEquals("SomeString2", allValues.get(1));
    }


    @Test
    public void spying() {
        // spy(class/interface) -> It will make a mock class with its original behaviour
        // ex:  Spying a list interface: spyObj.get(0) ==> it'll raise an exception because it has nothing
        // a mock it'd return the default => null
    }
}
