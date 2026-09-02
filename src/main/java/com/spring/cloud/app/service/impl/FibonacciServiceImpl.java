package com.spring.cloud.app.service.impl;

import com.spring.cloud.app.exception.InvalidInputException;
import com.spring.cloud.app.service.FibonacciService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class FibonacciServiceImpl implements FibonacciService {
    @Override
    public List<Integer> generateFibonacci(int n) {
        List<Integer> result = new ArrayList<>();
        log.info("Entered number: {}, for which Fibonacci value needs to be calculated", n);
        if (n <= 0) {
            throw  new InvalidInputException("Invalid Input: Number cannot be negative");
        }

        int a = 0;
        int b = 1;

        for (int i = 1; i <= n; i++) {
            result.add(a);

            int next = a + b;
            a = b;
            b = next;
        }

        return result;
    }
}
