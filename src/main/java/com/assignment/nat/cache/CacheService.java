package com.assignment.nat.cache;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private Map<Long, List<Long>> cache = new ConcurrentHashMap<>();

    public  Map<Long, List<Long>> getCacheData(){
        return cache;
    }

    public void setCacheData(Long number, List<Long> primeNumbers){
        cache.clear();
        cache.put(number,primeNumbers);
    }
}
