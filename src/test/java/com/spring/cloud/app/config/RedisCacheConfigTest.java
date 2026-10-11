package com.spring.cloud.app.config;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
/*
Concept
- employeeCacheConfiguration() creates the configuration object.
- The first test checks that the TTL is exactly 10 minutes.
- The second checks that a value serialization strategy is configured.
Important limitation: The second test does not prove that the serializer actually produces
valid JSON. Also, these tests call the configuration method directly, so they do not verify
Spring's @Configuration or @EnableCaching behavior.
 Your EmployeeRedisCacheTest is intended to verify caching through Spring.
 */
class RedisCacheConfigTest {

    private final RedisCacheConfig config = new RedisCacheConfig();

    @Test
    void employeeCacheConfiguration_ShouldSetTtlTo10Minutes() {

        RedisCacheConfiguration configuration =
                config.employeeCacheConfiguration();

        assertEquals(
                Duration.ofMinutes(10),
                configuration.getTtl()
        );
    }


    @Test
    void employeeCacheConfiguration_ShouldConfigureValueSerialization() {

        RedisCacheConfiguration configuration =
                config.employeeCacheConfiguration();

        assertNotNull(configuration);
        assertNotNull(configuration.getValueSerializationPair());
    }
}
