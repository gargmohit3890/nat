package com.assignment.nat.calculator;

import com.assignment.nat.domain.request.AlgoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PrimeNumberServiceFactory {

    private Map<AlgoType, PrimeNumberCalculator> algoMap;

    @Autowired
    private PrimeNumberServiceFactory(List<PrimeNumberCalculator> primeNumberCalculators) {
        algoMap = primeNumberCalculators.stream().collect(Collectors.toUnmodifiableMap(PrimeNumberCalculator::getAlgoType, Function.identity()));
    }

    public PrimeNumberCalculator getCalculator(AlgoType algoType) {
        return Optional.ofNullable(algoMap.get(algoType)).orElseThrow(IllegalArgumentException::new);
    }
}
