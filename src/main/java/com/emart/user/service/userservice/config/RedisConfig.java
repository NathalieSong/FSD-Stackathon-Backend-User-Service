package com.emart.user.service.userservice.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.emart.user.service.userservice.vo.TokenEntity;

@EnableAutoConfiguration
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, TokenEntity> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, TokenEntity> redisTemplate = new RedisTemplate<String, TokenEntity>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}