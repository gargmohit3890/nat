package com.assignment.nat.calculator;

import com.assignment.nat.domain.response.PrimeNumbers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SieveOfEratosthenePrimeNumberCalculatorTest {

    @InjectMocks
    private SieveOfEratosthenePrimeNumberCalculator calculatePrimeNumbers;

    @Test
    void testCalculatePrimeNumbers(){
        PrimeNumbers primeNumbers = calculatePrimeNumbers.calculatePrimeNumbers(2, 8);
        assertEquals(4, primeNumbers.getPrimeNumber().size());
    }

    @Test
    void testCalculatePrimeNumbersBetweenTwoNumbers(){
        PrimeNumbers primeNumbers = calculatePrimeNumbers.calculatePrimeNumbers(9, 13);
        assertEquals(2, primeNumbers.getPrimeNumber().size());
    }
}
