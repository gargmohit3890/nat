package com.assignment.nat.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CacheServiceImpl {

    @InjectMocks
    private CacheService cacheService;

    @BeforeEach
    void setUp(){
        Map<Long, List<Long>> cache = new ConcurrentHashMap<>();
        List<Long> primeNumbers = Arrays.asList(2L, 3L, 5L, 7L);
        cache.put(7L, primeNumbers);
        ReflectionTestUtils.setField(cacheService, "cache", cache);
    }

    @Test
    void testGetCache(){
        Map<Long, List<Long>> cache  = cacheService.getCacheData();
        assertEquals(1, cache.size());
        cache.forEach((largestPrimeNumber, list) -> {
            assertEquals(4, list.size());
        });
    }

    @Test
    void testsetCache(){
        List<Long> primeNumbers = Arrays.asList(2L, 3L, 5L);
        cacheService.setCacheData(5L, primeNumbers);
        Map<Long, List<Long>> cache  = cacheService.getCacheData();
        assertEquals(1, cache.size());
        cache.forEach((largestPrimeNumber, list) -> {
            assertEquals(3, list.size());
        });
    }
}
