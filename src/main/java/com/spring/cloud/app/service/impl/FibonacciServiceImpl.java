package com.spring.cloud.app.service.impl;

import com.spring.cloud.app.service.FibonacciService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FibonacciServiceImpl implements FibonacciService {
    @Override
    public List<Integer> generateFibonacci(int n) {
        List<Integer> result = new ArrayList<>();

        if (n <= 0) {
            return result;
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
