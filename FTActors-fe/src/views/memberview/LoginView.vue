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
          </div>
        </form>
        <button type="submit" class="btn btn-primary" @click="login">로그인</button>
        <div id="app" class="center">
          <a class="btn btn-kakao" href="http://localhost:8080/oauth2/authorization/kakao"><img src="@\assets\icons\KakaoLogin.png" alt=""></a>
      </div>
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

const memberStore = useMemberStore();
const jwtStore = useJwtStore();
const login = async () => {
  try {
    const response = await memberApi.login({ loginId: id.value,
      password: password.value});
    if(response.status === 200 || response.data.member != null){
      const responseData = response.data.data;
      // 로그인 성공 시 사용자 정보와 JWT 토큰을 저장
      console.log(responseData)
      const responseId = responseData.id;
      const responseJWT = responseData.accessToken;
      memberStore.setUser(responseId);
      jwtStore.setToken(responseJWT);
    }
    alert('로그인에 성공하였습니다')
        router.push("/");
    } catch (error) {
      alert('로그인에 실패하였습니다')
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