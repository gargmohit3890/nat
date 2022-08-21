package com.assignment.nat.service;


import com.assignment.nat.cache.CacheService;
import com.assignment.nat.calculator.Java8PrimeNumberCalculator;
import com.assignment.nat.calculator.PrimeNumberServiceFactory;
import com.assignment.nat.domain.request.AlgoType;
import com.assignment.nat.domain.request.NumberRequest;
import com.assignment.nat.domain.response.PrimeNumbers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrimeNumberServiceImplTest {

    @InjectMocks
    private PrimeNumberServiceImpl primeNumberService;

    @Mock
    private PrimeNumberServiceFactory primeNumberServiceFactory;

    @Mock
    private CacheService cacheService;

    @Mock
    private Java8PrimeNumberCalculator java8PrimeNumberCalculator;

    @Test
    void testCacheIsEmpty(){
        NumberRequest numberRequest = new NumberRequest();
        numberRequest.setNumber(100);
        when(cacheService.getCacheData()).thenReturn(new HashMap<>());
        PrimeNumbers response = new PrimeNumbers();
        response.setPrimeNumber(asList(2L,3L,5L));
        when(primeNumberServiceFactory.getCalculator(AlgoType.JAVA8)).thenReturn(java8PrimeNumberCalculator);
        when(java8PrimeNumberCalculator.calculatePrimeNumbers(2, 100)).thenReturn(response);
        PrimeNumbers primeNumbers = primeNumberService.calculate(numberRequest);
        assertEquals(3, primeNumbers.getPrimeNumber().size());
    }

    @Test
    void testCacheHasValueAndNumberUnderTestIsSmallerThanLastCalculatedNumber(){
        NumberRequest numberRequest = new NumberRequest();
        numberRequest.setNumber(5);
        Map<Long, List<Long>> cache = new HashMap<>();
        cache.put(6L,asList(2L,3L,5L));
        when(cacheService.getCacheData()).thenReturn(cache);
        PrimeNumbers primeNumbers = primeNumberService.calculate(numberRequest);
        assertEquals(3, primeNumbers.getPrimeNumber().size());
    }

    @Test
    void testCacheHasValueAndNumberUnderTestIsSmallerThanLastCalculatedNumber2(){
        NumberRequest numberRequest = new NumberRequest();
        numberRequest.setNumber(4);
        Map<Long, List<Long>> cache = new HashMap<>();
        cache.put(7L,asList(2L,3L,5L));
        when(cacheService.getCacheData()).thenReturn(cache);
        PrimeNumbers primeNumbers = primeNumberService.calculate(numberRequest);
        assertEquals(2, primeNumbers.getPrimeNumber().size());
    }

    @Test
    void testCacheHasValueAndNumberUnderTestIsEqualToLastCalculatedNumber(){
        NumberRequest numberRequest = new NumberRequest();
        numberRequest.setNumber(5);
        Map<Long, List<Long>> cache = new HashMap<>();
        cache.put(5L,asList(2L,3L,5L));
        when(cacheService.getCacheData()).thenReturn(cache);
        PrimeNumbers primeNumbers = primeNumberService.calculate(numberRequest);
        assertEquals(3, primeNumbers.getPrimeNumber().size());
    }

    @Test
    void testCacheHasValueAndNumberUnderTestIsGreaterThanLastCalculatedNumber(){
        NumberRequest numberRequest = new NumberRequest();
        numberRequest.setNumber(6);
        Map<Long, List<Long>> cache = new HashMap<>();
        cache.put(5L,asList(2L,3L,5L));
        when(cacheService.getCacheData()).thenReturn(cache);
        PrimeNumbers response = new PrimeNumbers();
        response.setPrimeNumber(asList());
        when(primeNumberServiceFactory.getCalculator(AlgoType.JAVA8)).thenReturn(java8PrimeNumberCalculator);
        when(java8PrimeNumberCalculator.calculatePrimeNumbers(6, 6)).thenReturn(response);
        PrimeNumbers primeNumbers = primeNumberService.calculate(numberRequest);
        assertEquals(3, primeNumbers.getPrimeNumber().size());
    }

    @Test
    void testCacheHasValueAndNumberUnderTestIsGreaterThanLastCalculatedNumber2(){
        NumberRequest numberRequest = new NumberRequest();
        numberRequest.setNumber(8);
        Map<Long, List<Long>> cache = new HashMap<>();
        List<Long> list = new ArrayList<>();
        list.add(2L);
        list.add(3L);
        list.add(5L);
        cache.put(5L,list);
        when(cacheService.getCacheData()).thenReturn(cache);
        PrimeNumbers response = new PrimeNumbers();
        List<Long> list1 = new ArrayList<>();
        list1.add(7L);
        response.setPrimeNumber(list1);
        when(primeNumberServiceFactory.getCalculator(AlgoType.JAVA8)).thenReturn(java8PrimeNumberCalculator);
        when(java8PrimeNumberCalculator.calculatePrimeNumbers(6, 8)).thenReturn(response);
        PrimeNumbers primeNumbers = primeNumberService.calculate(numberRequest);
        assertEquals(4, primeNumbers.getPrimeNumber().size());
    }
}
