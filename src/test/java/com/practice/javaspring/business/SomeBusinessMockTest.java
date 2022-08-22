package com.practice.javaspring.business;

import com.practice.javaspring.data.SomeDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@RunWith(JunitRunner...) Junit4
@ExtendWith(MockitoExtension.class) //Junit5 --> Spring boot 2.7.2 default
class SomeBusinessMockTest {

    @InjectMocks
    private SomeBusinessImpl business;// = new SomeBusinessImpl();<t
    @Mock
    private SomeDataService dataServiceMock;// = mock(SomeDataService.class);

    // This setter BeforeEach below will the same as using Annotations:
    // Using  to our test class with @InjectMocks to our dependency with @Mock
    // And for each test it will be an injection like below.
   /* @BeforeEach
    public void beforeEach() {
        business.setSomeDataService(dataServiceMock);
    }*/

    @Test
    public void calculateSumUsingDataService_basic() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});
        assertEquals(6, business.calculateSumUsingDataService());
        //int actualResult = business.calculateSumUsingDataService();
        //int expectedResult = 6;
        //assertEquals(expectedResult, actualResult);
    }


    @Test
    public void calculateSum_empty() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0, business.calculateSumUsingDataService());
    }

    @Test
    public void calculateSum_oneValue() {
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{6});
        assertEquals(6, business.calculateSumUsingDataService());
    }
}

