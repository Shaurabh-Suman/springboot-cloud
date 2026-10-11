package com.spring.cloud.app.controller;

import com.spring.cloud.app.model.Employee;
import com.spring.cloud.app.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/search")
    public Employee getEmployeeByIdUsingRequestParam(@RequestParam Long id) {
        return employeeService.getEmployeeById(id);
    }
}
/*
Both ways are correct, but I recommend `@RequiredArgsConstructor` with a `final` field for your Spring Boot project.

### Comparison

1\. Recommended — Lombok

```
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

}
```

- Less boilerplate code.
- Constructor is generated automatically by Lombok.
- Spring injects `EmployeeService` through the constructor.
- `final` ensures the dependency is initialized once and cannot be reassigned.

2\. Also correct — Manual constructor

```
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
```

- No Lombok required.
- You write the constructor yourself.
- Spring still injects the dependency through the constructor.

### Which should you use?

| Situation                        | Recommendation             |
| -------------------------------- | -------------------------- |
| Your project already uses Lombok | `@RequiredArgsConstructor` |
| You don't want Lombok            | Manual constructor         |
| Spring Boot production project   | Either approach is correct |

My recommendation: Continue with `@RequiredArgsConstructor` because your project already uses Lombok.

One important point: `@RequiredArgsConstructor` is a Lombok feature, not a Spring annotation. Spring performs the dependency injection; Lombok simply generates the constructor.
 */