package com.spring.cloud.app.controller.impl;

import com.spring.cloud.app.controller.FibonacciController;
import com.spring.cloud.app.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FibonacciControllerImpl implements FibonacciController {
    @Autowired
    FibonacciService fibonacciService;

    /*
     @Override
    @GetMapping("/fibonacci")
    public ResponseEntity<List<Integer>> getFibonacci(@RequestParam int n) {
        return ResponseEntity.ok( fibonacciService.generateFibonacci(n));
    }
     */

    @Override
    @GetMapping("/fibonacci")
    public ResponseEntity<Map<String, Object>> getFibonacci(
            @RequestParam int n) {

        List<Integer> fibonacciList =
                fibonacciService.generateFibonacci(n);

        Map<String, Object> response = new HashMap<>();

        response.put("message",
                "Fibonacci series generated successfully");
        response.put("data", fibonacciList);

        return ResponseEntity.ok(response);
    }
}
