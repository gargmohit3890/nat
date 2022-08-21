package com.assignment.nat.api;

import com.assignment.nat.service.PrimeNumberServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value=PrimeNumberController.class)
public class PrimeNumberControllerTest {

    @InjectMocks
    private PrimeNumberController primeNumberController;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PrimeNumberServiceImpl primeNumberService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(primeNumberController).build();
    }

    @Test
    void testGetPrimeNumber() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/primenumbers?number=5"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andDo(result -> assertEquals(200, result.getResponse().getStatus()));
    }

    @Test
    void testGetPrimeNumberWithInvalidNumber() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/primenumbers?number=-1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        resultActions.andDo(result -> assertEquals(400, result.getResponse().getStatus()));
    }

    @Test
    void testGetPrimeNumberWithInvalidAlgo() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/primenumbers?algorithm=1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
        resultActions.andDo(result -> assertEquals(400, result.getResponse().getStatus()));
    }


}
