package com.assignment.nat.calculator;

import com.assignment.nat.domain.request.AlgoType;
import com.assignment.nat.domain.response.PrimeNumbers;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class Java8PrimeNumberCalculator implements PrimeNumberCalculator {
    private static final AlgoType algo = AlgoType.JAVA8;

    @Override
    public AlgoType getAlgoType() {
        return algo;
    }

    @Override
    public PrimeNumbers calculatePrimeNumbers(long lastCachedPrimeNumber, long number) {
        PrimeNumbers primeNumbers = new PrimeNumbers();
        primeNumbers.setPrimeNumber(LongStream.rangeClosed(lastCachedPrimeNumber, number)
                .filter(x -> isPrime(x)).boxed()
                .collect(Collectors.toList()));
        return primeNumbers;
    }

    private static boolean isPrime(long number) {
        return LongStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(n -> number % n != 0);
    }
}
