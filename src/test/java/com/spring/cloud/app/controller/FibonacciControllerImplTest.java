package com.spring.cloud.app.controller;


import com.spring.cloud.app.controller.impl.FibonacciControllerImpl;
import com.spring.cloud.app.service.FibonacciService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FibonacciControllerImplTest {
    @Mock
    private FibonacciService fibonacciService;

    @InjectMocks
    private FibonacciControllerImpl fibonacciController;

    @Test
    void getFibonacci_ShouldReturnSuccessResponse() {

        List<Integer> fibonacciList =
                List.of(0, 1, 1, 2, 3);

        when(fibonacciService.generateFibonacci(5))
                .thenReturn(fibonacciList);

        ResponseEntity<Map<String, Object>> response =
                fibonacciController.getFibonacci(5);

        assertEquals(200,
                response.getStatusCode().value());

        assertNotNull(response.getBody());

        assertEquals(
                "Fibonacci series generated successfully",
                response.getBody().get("message"));

        assertEquals(
                fibonacciList,
                response.getBody().get("data"));
    }
}
