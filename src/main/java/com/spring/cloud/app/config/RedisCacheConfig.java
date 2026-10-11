package com.spring.cloud.app.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisCacheConfig {
    @Bean
    public RedisCacheConfiguration employeeCacheConfiguration() {
        // Why? We need a starting configuration for our employee cache.
        return RedisCacheConfiguration.defaultCacheConfig()

                // Sets the expiration time (TTL = Time To Live) to 10 minutes.
                // Why? Cached employee data should not remain in Redis forever.
                .entryTtl(Duration.ofMinutes(10))

                // Configures how Java objects are converted before Redis stores them.
                // Why? Redis stores data as bytes, so Java objects need serialization.
                .serializeValuesWith(

                        // Defines the serialization strategy for cached values.
                        // Why? We want to store employee objects in JSON format.
                        RedisSerializationContext.SerializationPair.fromSerializer(

                                // Converts Java objects into JSON and JSON back into objects.
                                // Why? Employee data can be stored and retrieved as JSON.
                                new GenericJackson2JsonRedisSerializer()
                        )
                );
    }
}