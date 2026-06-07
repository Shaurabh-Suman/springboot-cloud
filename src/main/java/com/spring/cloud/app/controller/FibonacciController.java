package com.spring.cloud.app.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface FibonacciController {
  // ResponseEntity< List<Integer>> getFibonacci(int n);
  ResponseEntity<Map<String, Object>> getFibonacci(int n);

}
