package com.a602.actors.global.jwt.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenUtil {
    private final RedisTemplate<String, String> redisTemplate;
    private final JWTUtil jwtUtil;

    public void setRefreshToken(String refreshToken) {
        redisTemplate.opsForValue().set("token" + jwtUtil.getLoginMemberId(), refreshToken);
    }

    public String getRefreshToken() {
        log.info("Redis에 저장되어 있는 토큰 ::::::::::::::: {}", redisTemplate.opsForValue().get("token" + jwtUtil.getLoginMemberId()));
        return redisTemplate.opsForValue().get("token" + jwtUtil.getLoginMemberId());
    }

    public boolean deleteRefreshToken() {
        return redisTemplate.delete("token" + jwtUtil.getLoginMemberId());
    }

    public boolean checkRefreshTokenEquals(String refreshToken) {
        return refreshToken.equals(getRefreshToken());
    }
}
