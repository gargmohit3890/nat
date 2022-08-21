package com.assignment.nat.api;

import com.assignment.nat.Application;
import com.assignment.nat.domain.response.PrimeNumbers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrimeNumberControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetPrimeNumber() throws Exception {
        ResponseEntity<PrimeNumbers> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/assignment/v1/primenumbers?number=5", PrimeNumbers.class);
        assertEquals(3, responseEntity.getBody().getPrimeNumber().size());
    }

    @Test
    void testGetPrimeNumberWithBadRequest() throws Exception {
        ResponseEntity<PrimeNumbers> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/assignment/v1/primenumbers?number=1", PrimeNumbers.class);
        assertTrue( responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    void testGetPrimeNumberForStringValue() throws Exception {
        ResponseEntity<PrimeNumbers> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/assignment/v1/primenumbers?number=A", PrimeNumbers.class);
        assertTrue( responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    void testGetPrimeNumberForAlgoInvalidValue() throws Exception {
        ResponseEntity<PrimeNumbers> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/assignment/v1/primenumbers?number=3&algorithm=ndnd", PrimeNumbers.class);
        assertTrue( responseEntity.getStatusCode().is4xxClientError());
    }

    @Test
    void testGetPrimeNumberSOE() throws Exception {
        ResponseEntity<PrimeNumbers> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/assignment/v1/primenumbers?number=3&algorithm=SOE", PrimeNumbers.class);
        assertEquals(2  , responseEntity.getBody().getPrimeNumber().size());
    }



}
