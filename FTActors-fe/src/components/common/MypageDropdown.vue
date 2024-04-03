<template>
    <div class="dropdown">

        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
        <img src="@/assets/icons/DefaultProfile.png" alt="">
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#" @click="goToLogout, logout()">로그아웃</a></li>
          <li><a class="dropdown-item" href="#" @click="goToMypage">마이페이지</a></li>
        </ul>
      </div>
</template>

<script setup>
import {  useRouter } from 'vue-router'
import { useMemberStore , useJwtStore } from "@/stores/member-store.js";

const router = useRouter();
const memberStore = useMemberStore();
const jwtStore = useJwtStore();

const goToMypage = () => {
  router.push({ name: 'mypage' });
};

const goToLogout = () => {
  router.push({ name: 'logout' });
};

const logout = async () => {
  try {
    // 사용자 상태와 JWT 토큰을 초기화 또는 제거
    memberStore.clearUser();
    jwtStore.deleteToken();
    
    alert('로그아웃 되었습니다.');
    
    // 로그아웃 후 홈 페이지 또는 로그인 페이지로 리다이렉트
    router.push('/login');
  } catch (error) {
    console.error('로그아웃 중 오류 발생', error);
    alert('로그아웃 중 문제가 발생했습니다.');
  }
};

</script>

<style>
.search-bar{
    display: flex;
}

.dropdown button img {
    width: 20px;
    height: 20px;
}
</style>