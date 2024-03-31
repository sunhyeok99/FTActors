<template>

  <div class="row">
    <div class="col-md-3">
      <div>
        <div>
          <h1><b>로그인</b></h1>
        </div>
        <form>
          <div class="form-group">
            <label>아이디</label>
            <input
            type="text"
            id="id"
            v-model="id"
            placeholder="아이디를 입력해주세요"
            class="input-field"
          />

          </div>
          <div class="form-group">
            <label>비밀번호</label>
            <input
              :type="showPassword ? 'text' : 'password'"
              id="password"
              v-model="password"
              placeholder="비밀번호를 입력해주세요"
              class="input-field"
            />
          </div>
          <div class="my-3 logins">
            <button type="submit" class="btn btn-primary" @click="login">로그인</button>
            <div id="app" class="center">
              <a class="btn btn-kakao" href="http://localhost:8080/oauth2/authorization/kakao"><img src="@\assets\icons\KakaoLogin.png" alt=""></a>
          </div>
          </div>
        </form>
      </div>
      <button type="button" class="btn" @click="goToJoin">아직 회원이 아니라면?</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from 'vue-router'
import { memberApi } from "@/util/axios";
import { useMemberStore, useJwtStore } from "@/stores/member-store";


const id = ref("");
const password = ref("");
const showPassword = ref(false);

const router = useRouter();


const login = async () => {
  try {
    let formData = new FormData();
formData.append("loginId", id.value);
formData.append("password", password.value);
  console.log(formData.get("loginId"))
  console.log(formData.get("password"))
    const response = await memberApi.login({formData});
    console.log(response)
    if(response.status === 200 || response.data.member != null){
      const responseData = response.data;
      // 로그인 성공 시 사용자 정보와 JWT 토큰을 저장
      const responseMember = responseData.member;
      const responseJWT = responseData.accessToken;

      member.value = {
        id: responseMember.Id,
        name: responseMember.name,
        stageName: responseMember.stageName,
        email: responseMember.email,
        profile : responseMember.profileImage,
        phone : responseMember.phone,
        createdAt : responseMember.createdAt
      };

      const memberStore = useMemberStore();
      const jwtStore = useJwtStore();
      userStore.setUser(member.value);
      jwtStore.setToken(responseToken);

    }
        router.push("/");
    } catch (error) {
      console.error('로그인에 실패했습니다.', error);
      router.push("/");
    }
};

const goToJoin = () => {
  router.push({ name: 'join' });
};
const goToKakaoLogin = () => {
  router.push({ name: 'kakaoLogin' });
};
// 비밀번호 표시 토글 기능
const togglePasswordVisibility = () => {
  showPassword.value = !showPassword.value;
};

</script>
<style>
.logins{
  display: flex;
  justify-content: space-between;
}
.kakaologin{
  cursor: pointer;
}

</style>