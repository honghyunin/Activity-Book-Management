package com.CRUD.test.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class RedisUtil {
    private final StringRedisTemplate stringRedisTemplate;

    public String getData(String key){
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setData(String key, String value){
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void setDataExpire(String key, String value, long duration){
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration); //Duration 지속시간 설정
        valueOperations.set(key, value, expireDuration); // 토큰의 지속시간을 설정해줌
    }

    public void deleteData(String key){
        stringRedisTemplate.delete(key);
    }
}