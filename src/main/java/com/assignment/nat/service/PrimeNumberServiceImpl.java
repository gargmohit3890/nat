package com.assignment.nat.service;

import com.assignment.nat.cache.CacheService;
import com.assignment.nat.calculator.PrimeNumberServiceFactory;
import com.assignment.nat.domain.request.NumberRequest;
import com.assignment.nat.domain.response.PrimeNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PrimeNumberServiceImpl implements  PrimeNumberService{

    @Autowired
    private PrimeNumberServiceFactory primeNumberServiceFactory;

    @Autowired
    private CacheService cacheService;

    @Override
    public PrimeNumbers calculate(NumberRequest numberRequest) {

        Map<Long, List<Long>> data = cacheService.getCacheData();
        List<Long> primeNumberList;
        final AtomicInteger currentListLength =  new AtomicInteger();
        if(!data.isEmpty()){
            PrimeNumbers response = new PrimeNumbers();
            data.forEach((largestNumber, list) -> {
                currentListLength.set(list.size());
                if(numberRequest.getNumber() == largestNumber) {
                    response.setPrimeNumber(list);
                }else if(numberRequest.getNumber() < largestNumber){
                    for (Long primeNumber: list) {
                        if(primeNumber == numberRequest.getNumber()){
                            response.setPrimeNumber(list.subList(0, list.indexOf(primeNumber) +1 ));
                            break;
                        }else if(primeNumber > numberRequest.getNumber()){
                            response.setPrimeNumber(list.subList(0, list.indexOf(primeNumber)));
                                break;
                        }
                    }
                } else {
                    PrimeNumbers primeNumbers = primeNumberServiceFactory.getCalculator(numberRequest.getAlgorithm()).calculatePrimeNumbers(list.get(list.size()-1)+ 1, numberRequest.getNumber());
                   //&& primeNumbers.getPrimeNumber().get(primeNumbers.getPrimeNumber().size()-1) != list.get(list.size()-1)
                    if(!primeNumbers.getPrimeNumber().isEmpty()){
                        list.addAll(primeNumbers.getPrimeNumber());
                    }
                    //Set<Long> temp = new LinkedHashSet<>(list);
                    response.setPrimeNumber(list);
                }
            });

            primeNumberList = response.getPrimeNumber();
            if(primeNumberList.size() > currentListLength.get()){
               cacheService.setCacheData(primeNumberList.get(primeNumberList.size()-1), primeNumberList);
                //cacheService.setCacheData(numberRequest.getNumber(), primeNumberList);
            }
            return response;
        } else {
            PrimeNumbers result = primeNumberServiceFactory.getCalculator(numberRequest.getAlgorithm()).calculatePrimeNumbers(2, numberRequest.getNumber());
            primeNumberList = result.getPrimeNumber();
            cacheService.setCacheData(primeNumberList.get(primeNumberList.size()-1), primeNumberList);
            //cacheService.setCacheData(numberRequest.getNumber(), primeNumberList);
            return result;
        }
    }
}
