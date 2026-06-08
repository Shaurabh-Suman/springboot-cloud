package com.spring.cloud.app.service;

import com.spring.cloud.app.exception.InvalidInputException;
import com.spring.cloud.app.service.impl.FibonacciServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciServiceImplTest {

    private final FibonacciServiceImpl service =
            new FibonacciServiceImpl();

    @Test
    void generateFibonacci_WhenInputIsFive() {

        List<Integer> result =
                service.generateFibonacci(5);

        assertEquals(
                List.of(0, 1, 1, 2, 3),
                result);
    }

    @Test
    void generateFibonacci_WhenInputIsZero() {

        InvalidInputException exception =
                assertThrows(
                        InvalidInputException.class,
                        () -> service.generateFibonacci(0));

        assertEquals(
                "Invalid Input: Number cannot be negative",
                exception.getMessage());
    }

    @Test
    void generateFibonacci_WhenInputIsNegative() {

        assertThrows(
                InvalidInputException.class,
                () -> service.generateFibonacci(-5));
    }
}