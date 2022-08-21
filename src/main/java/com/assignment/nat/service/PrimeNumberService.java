package com.assignment.nat.service;

import com.assignment.nat.domain.request.NumberRequest;
import com.assignment.nat.domain.response.PrimeNumbers;

public interface PrimeNumberService {

    PrimeNumbers calculate(NumberRequest numberRequest);
}
