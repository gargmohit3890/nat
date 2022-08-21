package com.assignment.nat.api;

import com.assignment.nat.domain.request.NumberRequest;
import com.assignment.nat.domain.response.PrimeNumbers;
import com.assignment.nat.service.PrimeNumberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/primenumbers")
@Log4j2
public class PrimeNumberController {

    @Autowired
    private PrimeNumberService primeNumberService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PrimeNumbers getProduct(@Valid NumberRequest numberRequest)
    {
        log.trace("Request received");
        return primeNumberService.calculate(numberRequest);
    }

    private PrimeNumbers getPrimeNumbers(NumberRequest numberRequest) {
        return null;
    }
}
