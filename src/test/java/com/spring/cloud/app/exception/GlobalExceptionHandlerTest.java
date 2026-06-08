package com.spring.cloud.app.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler
            handler = new GlobalExceptionHandler();

    @Test
    void handleInvalidInputException_ShouldReturnBadRequest() {

        InvalidInputException exception =
                new InvalidInputException(
                        "Invalid Input: Number cannot be negative");

        ResponseEntity<String> response =
                handler.handleInvalidInputException(
                        exception);

        assertEquals(
                400,
                response.getStatusCode().value());

        assertEquals(
                "Invalid Input: Number cannot be negative",
                response.getBody());
    }
}
