package com.a602.actors.global.jwt.admin;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.jwt.dto.JwtDto;
import com.a602.actors.global.jwt.service.JWTMemberServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Component
public class AdminAccountInitializer {

    private final JWTMemberServiceImpl jwtMemberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    public AdminAccountInitializer(JWTMemberServiceImpl jwtMemberService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtMemberService = jwtMemberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void createAdminAccount() {
        // 어드민 계정이 이미 존재하는지 확인
        if (!jwtMemberService.isAdminAccountExists(adminUsername)) {
            // 어드민 계정 생성
            JwtDto.Simple adminUser = new JwtDto.Simple();
            adminUser.setLoginId(adminUsername);
            adminUser.setPassword(bCryptPasswordEncoder.encode(adminPassword));
            // 이하 어드민 권한 등 설정
            jwtMemberService.signup(adminUser);
        }
    }
}
