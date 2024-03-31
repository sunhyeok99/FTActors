<template>
  <!-- 몽타쥬 사이드바로 가는 네비게이션 바 -->
  <MontageNav/>
  <div class="wrapper">
    <header>
      <div>
        <!-- 네비게이션 바 -->
        <nav id="menu" >
          <RouterLink to="/board">진행중인 공고</RouterLink>
          <RouterLink to="/profile">배우 프로필</RouterLink>
          <RouterLink to="/montagemain" id="fontapply">Montage</RouterLink>
          <RouterLink to="/report">신고 목록</RouterLink>
          <RouterLink to="/blacklist">블랙리스트</RouterLink>
          <div class="pageright">
            <!-- 알람 -->
            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal">
              <img src="@/assets/icons/Alarm.png" alt="">
            </button>
            <AlarmModal />
            <!-- 로그인 -->
            <button type="button" class="btn btn-dark" id="loginbtn" @click="goToLogin">로그인</button>
            <!-- 마이페이지 -->
            <MypageDropdown />
          </div>
        </nav>

      </div>
    </header>
    <!-- 라우팅된 화면 -->
    <RouterView />
    <!-- 메시지 버튼 -->
    <button class="btn btn-primary" id="floating-map-button" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions"
    aria-controls="offcanvasWithBothOptions"> <img width="40" src="@/assets/icons/Message.png" alt="message icon"></button>
    <SideBars />
  </div>
  <!-- 푸터 -->
  <footer>
    <FooterBox />
  </footer>
</template>

<script setup>

import { RouterLink, RouterView, useRouter } from 'vue-router'
import MontageNav from '@/components/montagepage/MontageNav.vue'
import MypageDropdown from './components/common/MypageDropdown.vue';
import FooterBox from './components/common/FooterBox.vue';
import AlarmModal from './components/modals/AlarmModal.vue';
import SideBars from './components/common/SideBars.vue';


const router = useRouter();
const goToLogin = () => {
  router.push({ name: 'login' });
};


</script>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

#loginbtn {
  min-width: 75px;
}
#fontapply {
  font-family: 'tuesday_nightregular', impact;
}

.montagemain-overlay {
  background-color: rgba(0, 0, 0, 0.7); /* 어두운 오버레이 */
}

/* 네비게이션 바 폰트 색상을 밝게 만드는 스타일 */
.light-font a {
  color: #ffffff; /* 밝은 폰트 색상 */
}


#menu {
  width: 100%;
  font-size: 20px;
  text-align: left;
  margin-top: 2rem;
  display: flex;
  align-items: center;
}

#menu a.router-link-exact-active {
  color: var(--color-text);
}

#menu a.router-link-exact-active:hover {
  background-color: transparent;
}

#menu a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

#menu a:first-of-type {
  border: 0;
}

#loginbtn {
  border-radius: 25px;
  /* 그라데이션 적용 */
  background-image: linear-gradient(to right, rgb(58, 123, 213), rgb(39, 16, 171));
  border: none;
  /* 기존 border 설정을 수정 */
}

@media (min-width: 1024px) {
  #menu {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;
    padding: 1rem 0;
    margin-top: 1rem;
  }
}

.pageright {
  display: flex;
  margin-left: auto;
  align-items: center;
}

.pageright button img {
  width: 24px;
  height: 24px;
}

.pageright img {
  width: 24px;
  height: 24px;
}

.wrapper {
  padding: 2rem;
}


#floating-map-button {
  position: fixed;
  display: flex;
  padding: 14px 14px;
  align-items: center;
  left: 88%;
  bottom: 10%;
  background: white;
  border-radius: 50%;
  transform: translateX(-50%);
  color: black;
  border: black solid 2px;
  gap: 20px;
  cursor: pointer;
  z-index: 10;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
</style>
