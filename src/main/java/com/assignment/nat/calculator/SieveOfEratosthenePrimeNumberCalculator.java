package com.assignment.nat.calculator;

import com.assignment.nat.domain.request.AlgoType;
import com.assignment.nat.domain.response.PrimeNumbers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SieveOfEratosthenePrimeNumberCalculator implements PrimeNumberCalculator {
    private static final AlgoType algo = AlgoType.SOE;

    @Override
    public AlgoType getAlgoType() {
        return algo;
    }

//    @Override
//    public PrimeNumbers calculatePrimeNumbers(long lastCachedPrimeNumber, long number) {
//        PrimeNumbers primeNumbers = new PrimeNumbers();
//        final boolean prime[] = new boolean[(int)number + 1];
//        Arrays.fill(prime, true);
//
//        for (int p = 2; p * p <= number; p++) {
//            if (prime[p]) {
//                for (int i = p * 2; i <= number; i += p)
//                    prime[i] = false;
//            }
//        }
//
//        final List<Long> primes = new ArrayList<>();
//        for (long i = lastCachedPrimeNumber; i <= number; i++) {
//            if (prime[(int)i])
//                primes.add(i);
//        }
//        primeNumbers.setPrimeNumber(primes);
//        return primeNumbers;
//    }

    public PrimeNumbers calculatePrimeNumbers(long lastCachedPrimeNumber, long number) {
        PrimeNumbers primeNumbers = new PrimeNumbers();
//        final boolean prime[] = new boolean[(int)number + 1];
//        Arrays.fill(prime, true);
        //List<Boolean> change = new ArrayList(Arrays.asList(prime));
        List<Boolean> change = new ArrayList (Collections.nCopies ((int)number + 1, true));

        for (int p = 2; p * p <= number; p++) {
            if (change.get(p)) {
                for (int i = p * 2; i <= number; i += p){
                    //prime[i] = false;
                    change.set(i, false);
                }

            }
        }

        final List<Long> primes = new ArrayList<>();
        for (long i = lastCachedPrimeNumber; i <= number; i++) {
            if (change.get((int)i))
                primes.add(i);
        }
        primeNumbers.setPrimeNumber(primes);
        return primeNumbers;
    }
}
