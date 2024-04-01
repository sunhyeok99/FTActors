package com.a602.actors.global.jwt;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.jwt.dto.JwtDto;
import com.a602.actors.global.jwt.repository.JWTMemberRepository;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Component
@Slf4j
public class JwtTokenProvider {

    private static final String BEARER_TYPE = "Bearer ";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 10; // * 60;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 7;
    private final Key key;
    private final JWTMemberRepository jwtMemberRepository;

//    public JwtTokenProvider(JWTMemberRepository jwtMemberRepository){
//        this.jwtMemberRepository = jwtMemberRepository;
//        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//    }

    public JwtTokenProvider(@Value("${jwtKey}") String secretKey, JWTMemberRepository jwtMemberRepository){
        this.jwtMemberRepository = jwtMemberRepository;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
////    public JwtTokenProvider(JWTMemberRepository jwtMemberRepository) {
////        this.jwtMemberRepository = jwtMemberRepository;
////        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
////    }
//public JwtTokenProvider(JWTMemberRepository jwtMemberRepository) {
//    this.jwtMemberRepository = jwtMemberRepository;
//    this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//}


    //JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public JwtDto.AuthResponse generateToken(Authentication authentication){
        long now = new Date().getTime();

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
//        Optional<Member> member  = jwtMemberRepository.findByLoginId(authentication.getName());
//        Long temp = member.get().getId();
        return JwtDto.AuthResponse.builder()
                .id(jwtMemberRepository.findByLoginId(authentication.getName()).get().getId())
                .loginId(authentication.getName())
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();


    }
    // Jwt 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken){
        //jwt 복호화
        Claims claims = parseClaims(accessToken);

//        log.info("{}", claims.getId());
//        if (claims.get("auth") == null) {
//            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
//        }
        // UserDetails 객체를 만들어서 Authentication return
        // UserDetails: interface, User: UserDetails를 구현한 class
        UserDetails principal = new User(claims.getSubject(), "", new ArrayList<>());

        return new UsernamePasswordAuthenticationToken(principal, "", new ArrayList<>());

    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        }catch(ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
