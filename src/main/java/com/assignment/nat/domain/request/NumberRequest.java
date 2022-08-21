package com.assignment.nat.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class NumberRequest {
    @Min(value = 2, message = "The value must be greater than 1")
    private long number;
    private AlgoType algorithm = AlgoType.JAVA8;
}

