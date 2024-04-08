<template>
  <div :class="{ 'montage-page': isMontagePage }">
    <!-- 몽타쥬 사이드바로 가는 네비게이션 바 -->
    <MontageNav />
    <div class="wrapper">
      <img src="@/assets/icons/Light.png" alt="" id="light">
      <header>
        <div>
          <!-- 네비게이션 바 -->
          <nav id="menu">
            <RouterLink to="/board">진행중인 공고</RouterLink>
            <RouterLink to="/profile">배우 프로필</RouterLink>
            <RouterLink to="/montagemain" id="fontapply">Montage</RouterLink>
            <!-- 어드민 계정 -->
            <div v-if="loginMember === 6">
              <RouterLink to="/report">신고 목록</RouterLink>
              <RouterLink to="/blacklist">블랙리스트</RouterLink>
            </div>
              <div class="pageright" v-if="!loginMember">
                <!-- 로그인 -->
                <button type="button" class="btn btn-secondary" id="loginbtn" @click="goToLogin">로그인</button>
                <!-- 회원가입 -->
                <button type="button" class="btn btn-secondary" id="joinbtn" @click="goToJoin">회원가입</button>
              </div>
              <div class="pageright" v-else>
                <!-- 알람 -->
                <!-- <AlarmModal @unreadCountUpdated="handleUnreadCountUpdated" /> -->
                <!-- 마이페이지 -->
                <MypageDropdown />
              </div>
          </nav>
        </div>
      </header>
      <!-- 라우팅된 화면 -->
      <RouterView />
      <!-- 메시지 버튼 -->
      <button :class="{ 'btn': true, 'floating-map-button': isMontagePage }" id="floating-map-button" type="button"
        data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">
        <img width="40" src="@/assets/icons/Message.png" alt="message icon"></button>
      <ChatList />
    </div>

  </div>
  <footer>
    <FooterBox />
  </footer>
</template>

<script setup>
import { ref, watch, onMounted, watchEffect } from 'vue'
import { RouterLink, RouterView, useRouter, useRoute } from 'vue-router'
import MontageNav from '@/components/montagepage/MontageNav.vue'
import MypageDropdown from './components/common/MypageDropdown.vue';
import FooterBox from './components/common/FooterBox.vue';
import AlarmModal from './components/modals/AlarmModal.vue';
import ChatList from './components/chatpage/chatlist.vue';

import { useMemberStore } from "@/stores/member-store.js";

const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;

const route = useRoute();
const router = useRouter();

const alarmCount = ref();

// 라우터
const goToLogin = () => {
  router.push({ name: 'login' });
};
const goToJoin = () => {
  router.push({ name: 'join' });
};

// 몽타쥬페이지일 때 몽타쥬의 위치에 맞게 페이지 스크롤
const isMontagePage = ref(false);
watch(() => route.path, (newPath) => {
  isMontagePage.value = newPath === '/montagemain';
  if (isMontagePage.value) {
    console.log('몽타쥬페이지 라우팅')
    scrollToPosition();}
});

const scrollToPosition = () => {
  const scrollDistance = 12 * 16;
  window.scrollTo({
    top: scrollDistance,
    behavior: 'smooth'
  });
};

onMounted(() => {
  if (isMontagePage.value) {
    scrollToPosition();
  }
});


const handleUnreadCountUpdated = (count) => {
  alarmCount.value = count
};


watchEffect(() => {
  loginMember.value = MemberStore.memberInfo;
});

</script>

<style scoped>
html,
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

header {
  line-height: 1.5;
  max-height: 100vh;
}


#light {
  width: 100px;
  height: 100px;
  margin-left: 0;
}



/* 네비게이션 바 폰트 색상을 밝게 만드는 스타일 */
.light-font a {
  color: #ffffff;
  /* 밝은 폰트 색상 */
}

.montage-page {
  background-color: #000;
  padding: 0;
  margin: 0;
  transition: background-color 1s ease;
}

.montage-page #menu a,

.montage-page #menu #fontapply {
  color: #fff;
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
  flex-direction: row;
}

.pageright button img {
  width: 24px;
  height: 24px;
}

.pageright img {
  width: 24px;
  height: 24px;
}


.floating-map-button {
  background: black;
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

#loginbtn {
  min-width: 80px;
}

#joinbtn {
  min-width: 100px;
}
</style>
