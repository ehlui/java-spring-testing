package com.practice.javaspring.business;

import com.practice.javaspring.data.SomeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * More generic stub to avoid creating multiple stubs.
 *
 * Instead of creating a single stub for a single test case (having then lots of it)
 * Having a generic one to change its state/value.
 */
class SomeDataServiceStub implements SomeDataService {
    private int[] stubValue;

    public void setStubValue(int[] stubValue) {
        this.stubValue = stubValue;
    }

    @Override
    public int[] retrieveAllData() {
        return stubValue;
    }
}

class SomeBusinessImplTest {

    private SomeDataServiceStub someDataServiceStub;

    @BeforeEach
    public void setup() {
        someDataServiceStub = new SomeDataServiceStub();
    }

    @Test
    public void calculateSum_basic() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculateSum(new int[]{1, 2, 3});
        int expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void calculateSum_empty() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculateSum(new int[]{});
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSum_oneValue() {
        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculateSum(new int[]{7});
        int expectedResult = 7;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateSumUsingDataService() {
        SomeBusinessImpl business = new SomeBusinessImpl();

        someDataServiceStub.setStubValue(new int[]{1, 2, 3});

        business.setSomeDataService(someDataServiceStub);

        int actualResult = business.calculateSumUsingDataService();
        int expectedResult = 6;

        assertEquals(expectedResult, actualResult);
    }
}

