package com.a602.actors.global.auth.service.member;



import com.a602.actors.domain.member.Member;
import com.a602.actors.global.auth.domain.CustomOAuth2User;
import com.a602.actors.global.auth.repository.KakaoMemberRepository;
import com.a602.actors.global.auth.service.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MemberService extends DefaultOAuth2UserService{
    private final RedisService redisService;

    private final KakaoMemberRepository kakaoMemberRepository;

    public MemberService(RedisService redisService, KakaoMemberRepository kakaoMemberRepository){
        this.redisService = redisService;
        this.kakaoMemberRepository = kakaoMemberRepository;
    }

    @Override
    public CustomOAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 인증 객체 불러오기
        OAuth2User oauth2User = super.loadUser(userRequest);
        // 인증 객체 권한 정보 가져오기
        Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();

        String kakaoId = null, userName = null, email = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId();

        // oauth 타입에 따라 데이터가 다르기에 분기
        if("kakao".equals(oauthType.toLowerCase())) {
//            email = ((Map<String, Object>) attributes.get("kakao_account")).get("email").toString();
            // kakao는 kakao_account 내에 email이 존재함.
            kakaoId = attributes.get("id").toString();
            log.info("kakao id success : {}", kakaoId);
            userName = ((Map<String, Object>)((Map<String, Object>) attributes.get("kakao_account"))
                    .get("profile")).get("nickname").toString();
        }
        Member member = new Member();

        // UserEntity 존재여부 확인 및 없으면 생성
        Member check = getUserByKakaoId(kakaoId);

        if(check == null){
            // 시간 정보는 DB안에서 생성됨, 닉네임은 초기값으로 이메일로 통일
//            kakaoMember.setUserId(UUID.randomUUID().toString());
            member.setEmail(email);
            member.setName(userName);
            member.setKakaoId(kakaoId);
            member.setOauthType(oauthType);
            save(member);
        }else{
            // 회원 정보가 있다면 레코드 불러오기
            member = check;
            log.info("{}", member.getName());
        }

        return new CustomOAuth2User(oauth2User, member);
    }
    public void save(Member kakaoMember) {
        kakaoMemberRepository.save(kakaoMember);
    }

    public Member getUserByKakaoId(String kakaoId) {
        return kakaoMemberRepository.findByKakaoId(kakaoId).orElse(null);
    }

    public Member getUserByUserId(String userId) {
        return kakaoMemberRepository.findByUserId(userId).orElse(null);
    }

    public Boolean modifyUserLoginStatus(String accessToken) {
        try {
            redisService.updateExpireTime(accessToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void refreshRedisData(Map<String, Object> pastMapData, CustomOAuth2User oAuth2User,
                                  String accessToken) throws Exception{
        try {
            // 레디스에 저장된 인증객체 정보
            OAuth2AuthenticationToken pastOAuth2AuthenticationToken =
                    (OAuth2AuthenticationToken)pastMapData.get("authenticationToken");
            // 새로운 인증 객체로 갱신
            OAuth2AuthenticationToken newOAuth2AuthenticationToken =
                    new OAuth2AuthenticationToken(
                            oAuth2User,
                            pastOAuth2AuthenticationToken.getAuthorities(),
                            pastOAuth2AuthenticationToken.getAuthorizedClientRegistrationId()
                    );
            // 새로운 인증객체를 Security context에 반영
            SecurityContextHolder.getContext()
                    .setAuthentication(newOAuth2AuthenticationToken);

            Map<String, Object> updateMapData = new HashMap<>();
            updateMapData.put("authenticationToken", newOAuth2AuthenticationToken);
            updateMapData.put("oauth2User", oAuth2User);

            updateMapData.put("refreshToken", pastMapData.get("refreshToken"));
            updateMapData.put("createAt", pastMapData.get("createAt"));
            long time = redisService.getExpireTime(accessToken);
            // 기존 토큰 지우기
            redisService.deleteMapData(accessToken);
            log.info("new Time : {}", time);
            // 새로운 토큰 넣기
            redisService.saveMapData(accessToken, updateMapData, time);
        }
        catch(Exception e){
            throw new Exception();
        }
    }


}
