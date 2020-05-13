package com.emart.user.service.userservice.service;

import java.util.concurrent.TimeUnit;

import com.emart.user.service.userservice.jwtsecurity.JwtTokenUtil;
import com.emart.user.service.userservice.vo.TokenEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, TokenEntity> redisTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public TokenEntity getToken(String username) {
        return redisTemplate.opsForValue().get(username);
    }

    public void setToken(String username, String token) {
        redisTemplate.opsForValue().set(
            username,
            new TokenEntity(username, token),
            jwtTokenUtil.getExpirationPeriod(),
            TimeUnit.MICROSECONDS);
    }

    public void rmToken(String username) {
        redisTemplate.delete(username);
    }
}