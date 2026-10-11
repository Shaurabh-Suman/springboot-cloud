package com.spring.cloud.app.service;

import com.spring.cloud.app.model.Employee;
import com.spring.cloud.app.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.junit.jupiter.api.BeforeEach;
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

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:redisCacheTest",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop",

        "spring.data.redis.host=${test.redis.host:localhost}",
        "spring.data.redis.port=6379"
})
class EmployeeRedisCacheTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private EmployeeRepository repository;

    @BeforeEach
    void setUp() {
        Cache cache = cacheManager.getCache("employees");

        assertNotNull(cache, "Cache 'employees' must exist");

        cache.clear();
        reset(repository);
    }

    @Test
    void getEmployeeById_ShouldCacheResult() {
        // Arrange
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

        // Act: first call should retrieve the employee from the repository.
        Employee first = employeeService.getEmployeeById(id);

        // Act: second call should retrieve the employee from the cache.
        Employee second = employeeService.getEmployeeById(id);

        // Assert: both calls return the expected employee.
        assertNotNull(first);
        assertNotNull(second);
        assertEquals("John", first.getName());
        assertEquals("John", second.getName());

        // Assert: repository should be called only once.
        verify(repository, times(1)).findById(id);

        // Assert: employee should be present in the cache.
        Cache cache = cacheManager.getCache("employees");
        assertNotNull(cache);
        assertNotNull(cache.get(id));
    }
}