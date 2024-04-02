package com.a602.actors.global.jwt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import com.a602.actors.domain.member.Member;
import com.a602.actors.global.common.config.FileUtil;
import com.a602.actors.global.common.enums.FolderType;
import com.a602.actors.global.exception.CustomException;
import com.a602.actors.global.exception.MemberException;
import com.a602.actors.global.exception.TokenException;
import com.a602.actors.global.jwt.JwtTokenProvider;
import com.a602.actors.global.jwt.dto.JwtDto;
//import com.a602.actors.global.jwt.mapper.MemberMapper;
import com.a602.actors.global.jwt.repository.JWTMemberRepository;
import com.a602.actors.global.jwt.util.JWTUtil;
import com.a602.actors.global.jwt.util.TokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import static com.a602.actors.global.exception.ExceptionCodeSet.MEMBER_DUPLICATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class JWTMemberServiceImpl {
    private final JWTMemberRepository jwtMemberRepository;
    //private final MemberMapper memberMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenUtil tokenUtil;
    private final JWTUtil jwtUtil;

    @Transactional
    public String signup(JwtDto.Simple jwtDto, MultipartFile profileImage) throws IOException {

        JwtDto.checkIdResult idResult = isDuplicatedId(jwtDto.getLoginId());
        if (idResult.getIsDuplicate()) {
            throw new MemberException(MEMBER_DUPLICATED);
        }
        String encodePassword = bCryptPasswordEncoder.encode(jwtDto.getPassword());
        log.info("encodePassword : {}", encodePassword);
        jwtDto.setPassword(encodePassword);

        String savedName = "";
        String url = "";
        try{
            if(profileImage != null){
                savedName = FileUtil.makeFileName(profileImage.getOriginalFilename());
                url = FileUtil.uploadFile(profileImage, savedName, FolderType.PROFILE_PATH);
            }
            else {
                url = "";
            }

            Member member = Member.builder()
                    .loginId(jwtDto.getLoginId())
                    .password(jwtDto.getPassword())
                    .name(jwtDto.getName())
                    .email(jwtDto.getEmail())
                    .phone(jwtDto.getPhone())
                    .birth(jwtDto.getBirth())
                    .profileImage(url)
                    .stageName(jwtDto.getStageName())
                    .build();

            jwtMemberRepository.save(member);

        }catch (IOException e){
            // 프로필 이미지 업로드 실패 시 예외 처리
            log.error("Failed to upload profile image to S3", e);
            throw e;
        }
        return "";
    }

    @Transactional
    public JwtDto.AuthResponse signin(JwtDto.AuthRequest memberDto) {
        Member member = jwtMemberRepository.findByLoginId(memberDto.getLoginId())
                .orElseThrow(() -> new CustomException("일치하는 사용자가 존재하지 않습니다."));
        log.info("로그인 시도한 멤버 :::::::::: {}, {}", member.getId(), member.getPassword());
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(member.getLoginId(), member.getPassword(), new ArrayList<GrantedAuthority>());
//        authentication.setAuthenticated(true);
//        Authentication authentication = authenticationManagerBuilder.getObject()
//                .authenticate(memberDto.toAuthentication());
//                authenticationManagerBuilder.getObject()
//                .authenticate(memberDto.toAuthentication());
        // SecurityContextHolder에 로그인 한 유저 정보 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("로그인 후 SecurityContextHolder에 저장된 사용자 :::::: {}",
                SecurityContextHolder.getContext().getAuthentication().getName());

        JwtDto.AuthResponse authResponse = jwtTokenProvider.generateToken(authentication);
        log.info("Authentication : {}", authentication.toString());
//        log.info("어세스 토큰 : {}", authResponse.getAccessToken());
//        log.info("리프레시 토큰 : {}", authResponse.getRefreshToken());
        // Refresh Token Redis에 저장
        tokenUtil.setRefreshToken(authResponse.getRefreshToken());
//        String loginId = jwtUtil.getLoginMemberId();
        return authResponse;
    }
    public JwtDto.getPkId getIdByLoginId(String userId) {
        Optional<Member> optionalMember = jwtMemberRepository.findByLoginId(userId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return JwtDto.getPkId.builder()
                    .id(member.getId())
                    .loginId(member.getLoginId())
                    .name(member.getName())
                    .email(member.getEmail())
                    .phone(member.getPhone())
                    .birth(member.getBirth())
                    .gender(member.getGender())
                    .profileImage(member.getProfileImage())
                    .stageName(member.getStageName())
                    .createdAt(member.getCreatedAt()) // createdAt 그대로 넣기
                    .build();
        } else {
            return null;
        }
    }

    public JwtDto.AuthResponse reIssue(JwtDto.AuthResponse tokenRequest) {
        log.info("MemberService :::::::::: reIssue 시작 !!!!!!!");
        // Refresh Token 파싱되면 OK
        jwtTokenProvider.validateToken(tokenRequest.getRefreshToken());
        log.info("MemberService :::::::::: 유효한 토큰");
        // Access Token 파싱해서 새로운 인증객체 만들기
        Authentication authentication = jwtTokenProvider.getAuthentication(tokenRequest.getAccessToken());
        log.info("MemberService :::::::::: 인증객체 생성 {}", authentication.getName());
        // Redis에 저장되어있는 Refresh Token과 Request로 받은 Refresh Token 비교
        if (tokenUtil.checkRefreshTokenEquals(tokenRequest.getRefreshToken()) == false) {
            throw new TokenException("저장되어 있는 토큰과 일치하지 않습니다 !!!");
        }
        // 인증 객체로 토큰 재발행
        JwtDto.AuthResponse authResponse = jwtTokenProvider.generateToken(authentication);

        return authResponse;
    }

    /**
     * 로그인 id 중복 검사
     * @param memberId : 로그인할 때 쓰는 아이디
     * @return boolean 중복 여부. true면 중복(가입불가), false면 중복 없음 (가입 가능)
     */
    public JwtDto.checkIdResult isDuplicatedId(String memberId) {
        Optional<Member> member = jwtMemberRepository.findByLoginId(memberId);
        if (member.isPresent()) {
            throw new MemberException(MEMBER_DUPLICATED);
        }
        return new JwtDto.checkIdResult(false);
    }

    public boolean isAdminAccountExists(String adminUsername) {
        return jwtMemberRepository.findByLoginId(adminUsername).isPresent();
    }

    public void logout() {
        if (tokenUtil.deleteRefreshToken() == false) {
            throw new RuntimeException("저장된 토큰을 삭제할 수 없습니다 !!!!!");
        }
    }

    public void deleteMember(){
        //사용자가 올린 공고 삭제
        //사용자가 지원한 공고에 지원 내용 삭제
        //s3에 내가 올린 파일 모두 삭제
        //사용자의 위시리스트 삭제
        //프로필 삭제
        //사용자 정보 삭제
    }
//    public void deleteMember() {
//        logout();
//
//        jwtMemberRepository.delete(
//                jwtMemberRepository.findById(jwtUtil.getLoginMemberId())
//                        .orElseThrow(() -> new RuntimeException("일치하는 사용자가 없습니다 !!!!!"))
//        );
//    }
    @Transactional
    public void updateUser(JwtDto.UpdateRequest updateRequest) {
        Optional<Member> optionalMember = jwtMemberRepository.findByLoginId(updateRequest.getLoginId());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setName(updateRequest.getName());
            member.setEmail(updateRequest.getEmail());
            member.setPhone(updateRequest.getPhone());
            member.setBirth(updateRequest.getBirth());
            member.setGender(updateRequest.getGender());
            member.setStageName(updateRequest.getStageName());
            // 여기에 필요한 다른 정보들을 수정하는 코드를 추가할 수 있습니다.
            jwtMemberRepository.save(member);
            log.info("사용자 정보 수정 완료: {}", updateRequest.getLoginId());
        } else {
            throw new CustomException("사용자를 찾을 수 없습니다.");
        }
    }
}