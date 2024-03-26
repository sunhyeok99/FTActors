package com.a602.actors.global.auth.service.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    // 유저 데이터, refresh 토큰, 생성된 시간 저장 - 로그인 상태 유지(14일)
    public void saveMapData(String key, Map<String, Object> mapData) {
        redisTemplate.opsForValue();
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, mapData);
        redisTemplate.expire(key, 3, TimeUnit.HOURS);
    }

    // 유저 데이터, refresh 토큰, 생성된 시간 저장 - 정해진 시간
    public void saveMapData(String key, Map<String, Object> mapData, long time) {
        redisTemplate.opsForValue();
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, mapData);

        redisTemplate.expire(key, time, TimeUnit.MINUTES);
    }

    // 유저 데이터, refresh 토큰, 생성된 시간 가져오기
    public Map<String, Object> getMapData(String key) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    public void updateExpireTime(String key) {
        redisTemplate.expire(key, 14, TimeUnit.DAYS);
        log.info("만료시간: {}", redisTemplate.getExpire(key));
    }
    public Long getExpireTime(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MINUTES);
    }

    // 유저 데이터, refresh 토큰, 생성된 시간 제거
    public void deleteMapData(String key) {
        redisTemplate.delete(key);
    }




}
