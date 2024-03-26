package com.a602.actors.domain.member.service;

import com.a602.actors.domain.member.Member;
import com.a602.actors.domain.member.dto.MemberPlusDTO;
import com.a602.actors.domain.member.repository.MemberRepository;
import com.a602.actors.global.auth.repository.KakaoMemberRepository;
import com.a602.actors.global.auth.service.redis.RedisService;
import com.a602.actors.global.common.config.FileUtil;
import com.a602.actors.global.common.enums.FolderType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberPlusServiceImpl implements MemberPlusService {
    private final MemberRepository memberPlusRepository;
    private final KakaoMemberRepository kakaoMemberRepository;
    private final RedisService redisService;
    private final FileUtil fileUtil;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    public void register(MemberPlusDTO memberPlusDTO, MultipartFile profileImage, String kakaoId ) throws IOException {
        log.info("kakao_id : {}", kakaoId);
        Optional<Member> existingMemberOptional = kakaoMemberRepository.findByKakaoId(kakaoId);
//        Optional<Member> existingMemberOptional2 = kakaoMemberRepository.findById("6");

        log.info("{}", existingMemberOptional);
        if (existingMemberOptional.isPresent()) {
            Member existingMember = existingMemberOptional.get();
            // S3에 프로필 이미지 업로드 및 URL 가져오기
            try {
                // 이걸 저장해주세요
                String savedName = FileUtil.makeFileName(profileImage.getOriginalFilename());
                String profileImageUrl = FileUtil.uploadFile(profileImage, savedName, FolderType.PROFILE_PATH);
                System.out.println("URL : " + profileImageUrl);
                System.out.println("URL LENGTH: " + profileImageUrl.length());

                existingMember.setEmail(memberPlusDTO.getEmail());
                existingMember.setPhone(memberPlusDTO.getPhone());
                existingMember.setBirth(memberPlusDTO.getBirth());
                existingMember.setGender(memberPlusDTO.getGender());
                existingMember.setProfileImage(profileImageUrl);
                existingMember.setStageName(memberPlusDTO.getStageName());
                existingMember.setSavedName(savedName);
                memberPlusRepository.save(existingMember);
            } catch (IOException e) {
                // 프로필 이미지 업로드 실패 시 예외 처리
                log.error("Failed to upload profile image to S3", e);
                throw e;
            }
        } else {
            // Kakao 회원을 찾지 못한 경우에 대한 처리
            log.error("Kakao member with ID {} not found", kakaoId);
            // 예외를 던지거나 적절한 방법으로 처리
            // throw new RuntimeException("Kakao member not found");
            // 또는 다른 처리 방법을 선택
        }
    }
}
