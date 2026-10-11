package com.spring.cloud.app.service;

import com.spring.cloud.app.model.Employee;
import com.spring.cloud.app.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/*
Why a separate Redis test class?
- EmployeeServiceImplTest uses @InjectMocks to create the service directly. Spring's caching proxy is bypassed, so @Cacheable is not tested.
- EmployeeRedisCacheTest uses @SpringBootTest and @Autowired to get the Spring-managed service, allowing @Cacheable to work.
Core concept: Unit tests verify business logic; integration tests verify Spring's caching behavior.
Separate classes are recommended for clarity, but not mandatory.
 */

@SpringBootTest
//@ActiveProfiles("local")
class EmployeeRedisCacheTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private EmployeeRepository repository;

    @Test
    void getEmployeeById_ShouldCacheResult() {
        Long id = 1L;

        Employee employee = Employee.builder()
                .id(id)
                .name("John")
                .department("IT")
                .email("john@test.com")
                .salary(50000.0)
                .build();

        when(repository.findById(id))
                .thenReturn(Optional.of(employee));

        // First call: cache miss, so repository is called.
        Employee first = employeeService.getEmployeeById(id);

        // Second call: cache hit, so repository should not be called again.
        Employee second = employeeService.getEmployeeById(id);

        assertEquals("John", first.getName());
        assertEquals("John", second.getName());

        verify(repository, times(1)).findById(id);

        assertNotNull(cacheManager.getCache("employees"));
        assertNotNull(cacheManager.getCache("employees").get(id));
    }
}
