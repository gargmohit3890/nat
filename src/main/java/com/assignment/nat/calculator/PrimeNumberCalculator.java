package com.assignment.nat.calculator;

import com.assignment.nat.domain.request.AlgoType;
import com.assignment.nat.domain.response.PrimeNumbers;

public interface PrimeNumberCalculator {

    AlgoType getAlgoType();

    PrimeNumbers calculatePrimeNumbers(long lastCachedPrimeNumber, long number);
}
