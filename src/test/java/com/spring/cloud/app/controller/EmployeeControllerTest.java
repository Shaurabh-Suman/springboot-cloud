package com.spring.cloud.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.app.model.Employee;
import com.spring.cloud.app.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveEmployee() throws Exception {

        Employee employee = Employee.builder()
                .id(1L)
                .name("John")
                .department("IT")
                .email("john@test.com")
                .salary(50000.0)
                .build();

        when(employeeService.saveEmployee(any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void testGetAllEmployees() throws Exception {

        List<Employee> employees = List.of(
                Employee.builder().id(1L).name("John").build(),
                Employee.builder().id(2L).name("David").build()
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetEmployeeByIdUsingRequestParam() throws Exception {

        Employee employee = Employee.builder()
                .id(1L)
                .name("John")
                .department("IT")
                .email("john@test.com")
                .salary(50000.0)
                .build();

        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/search")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.email").value("john@test.com"))
                .andExpect(jsonPath("$.salary").value(50000.0));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }
}