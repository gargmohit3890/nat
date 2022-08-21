package com.assignment.nat.calculator;

import com.assignment.nat.domain.response.PrimeNumbers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class Java8PrimeNumberCalculatorTest {

    @InjectMocks
    private Java8PrimeNumberCalculator java8PrimeNumberCalculator;

    @Test
    void testCalculatePrimeNumbers() {
        PrimeNumbers primeNumbers = java8PrimeNumberCalculator.calculatePrimeNumbers(2, 8);
        assertEquals(4, primeNumbers.getPrimeNumber().size());
    }

    @Test
    void testCalculatePrimeNumbersBetweenTwoNumbers() {
        PrimeNumbers primeNumbers = java8PrimeNumberCalculator.calculatePrimeNumbers(9, 13);
    }
}