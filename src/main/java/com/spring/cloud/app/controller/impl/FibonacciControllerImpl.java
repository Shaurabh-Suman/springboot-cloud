package com.spring.cloud.app.controller.impl;

import com.spring.cloud.app.controller.FibonacciController;
import com.spring.cloud.app.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
public class FibonacciControllerImpl implements FibonacciController {
    @Autowired
    FibonacciService fibonacciService;

    @Override
    @GetMapping("/fibonacci")
    public List<Integer> getFibonacci(@RequestParam int n) {

        return fibonacciService.generateFibonacci(n);
    }
}
