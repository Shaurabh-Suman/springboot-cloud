package com.spring.cloud.app.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidInputExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {

        InvalidInputException exception =
                new InvalidInputException(
                        "Invalid Input: Number cannot be negative");

        assertEquals(
                "Invalid Input: Number cannot be negative",
                exception.getMessage());
    }
}