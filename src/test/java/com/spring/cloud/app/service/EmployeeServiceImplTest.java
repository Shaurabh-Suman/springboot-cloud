package com.spring.cloud.app.service;

import com.spring.cloud.app.model.Employee;
import com.spring.cloud.app.repository.EmployeeRepository;
import com.spring.cloud.app.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void testSaveEmployee() {

        Employee employee = Employee.builder()
                .id(1L)
                .name("John")
                .department("IT")
                .email("john@test.com")
                .salary(50000.0)
                .build();

        when(repository.save(any(Employee.class))).thenReturn(employee);

        Employee saved = service.saveEmployee(employee);

        assertNotNull(saved);
        assertEquals("John", saved.getName());

        verify(repository, times(1)).save(employee);
    }

    @Test
    void testGetAllEmployees() {

        when(repository.findAll()).thenReturn(List.of(
                Employee.builder().id(1L).name("John").build(),
                Employee.builder().id(2L).name("David").build()
        ));

        List<Employee> list = service.getAllEmployees();

        assertEquals(2, list.size());

        verify(repository).findAll();
    }

    @Test
    void testGetEmployeeById() {

        Employee employee = Employee.builder()
                .id(1L)
                .name("John")
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result = service.getEmployeeById(1L);

        assertEquals("John", result.getName());
    }

    @Test
    void testUpdateEmployee() {

        Employee existing = Employee.builder()
                .id(1L)
                .name("John")
                .department("IT")
                .email("john@test.com")
                .salary(50000.0)
                .build();

        Employee updated = Employee.builder()
                .name("David")
                .department("HR")
                .email("david@test.com")
                .salary(70000.0)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Employee result = service.updateEmployee(1L, updated);

        assertEquals("David", result.getName());
        assertEquals("HR", result.getDepartment());
        assertEquals(70000.0, result.getSalary());
    }

    @Test
    void testDeleteEmployee() {

        Employee employee = Employee.builder()
                .id(1L)
                .name("John")
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        service.deleteEmployee(1L);

        verify(repository).delete(employee);
    }

    @Test
    void testEmployeeNotFound() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.getEmployeeById(1L));

        assertEquals("Employee not found", ex.getMessage());
    }
}
