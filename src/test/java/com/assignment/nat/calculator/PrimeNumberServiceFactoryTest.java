package com.assignment.nat.calculator;

import com.assignment.nat.domain.request.AlgoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PrimeNumberServiceFactoryTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    void testGetJava8Caluclator() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Java8PrimeNumberCalculator java8PrimeNumberCalculator = new Java8PrimeNumberCalculator();
        List<PrimeNumberCalculator> primeNumberCalculators = new ArrayList<>();
        primeNumberCalculators.add(java8PrimeNumberCalculator);
        Constructor<PrimeNumberServiceFactory> constructor = PrimeNumberServiceFactory.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);
        PrimeNumberServiceFactory secondOb = constructor.newInstance(primeNumberCalculators);
        constructor.setAccessible(false);
        PrimeNumberCalculator primeNumberCalculator = secondOb.getCalculator(AlgoType.JAVA8);
        assertTrue(primeNumberCalculator instanceof Java8PrimeNumberCalculator);
    }

    @Test
    void testGetSOECaluclator() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SieveOfEratosthenePrimeNumberCalculator  sieveOfEratosthenePrimeNumberCalculator = new SieveOfEratosthenePrimeNumberCalculator();
        List<PrimeNumberCalculator> primeNumberCalculators = new ArrayList<>();
        primeNumberCalculators.add(sieveOfEratosthenePrimeNumberCalculator);
        Constructor<PrimeNumberServiceFactory> constructor = PrimeNumberServiceFactory.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);
        PrimeNumberServiceFactory secondOb = constructor.newInstance(primeNumberCalculators);
        constructor.setAccessible(false);
        PrimeNumberCalculator primeNumberCalculator = secondOb.getCalculator(AlgoType.SOE);
        assertTrue(primeNumberCalculator instanceof SieveOfEratosthenePrimeNumberCalculator);
    }
}
