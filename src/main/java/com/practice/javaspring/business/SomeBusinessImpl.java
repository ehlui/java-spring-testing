package com.practice.javaspring.business;

import com.practice.javaspring.data.SomeDataService;

import java.util.Arrays;
import java.util.OptionalInt;

public class SomeBusinessImpl {
    private SomeDataService someDataService;

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }

    public int calculateSum(int[] data) {
        OptionalInt number = Arrays.stream(data).reduce(Integer::sum);//Integer::sum ==  (subtotal,element) -> subtotal+element
        return number.orElse(0);
    }

    public int calculateSumUsingDataService() {
        int[] data = someDataService.retrieveAllData();
        return Arrays.stream(data).reduce(Integer::sum).orElse(0); //Integer::sum ==  (subtotal,element) -> subtotal+element
    }
}
