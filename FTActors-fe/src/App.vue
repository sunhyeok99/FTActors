<template>
<MontagePopup />
  <div class="wrapper">
    <header>
      <div>
        <nav>
          <RouterLink to="/" id="name">배우는 사람</RouterLink>
          <RouterLink to="/board">진행중인 공고</RouterLink>
          <RouterLink to="/profile">배우 프로필</RouterLink>
          <RouterLink to="/montage">몽타쥬</RouterLink>
          <div class="pageright">
            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal">
              <img src="@/assets/Alarm.png" alt="">
            </button>
            <AlarmModal />
            <button type="button" class="btn btn-dark" id="loginbtn" @click="goToLogin">로그인</button>
            <MypageDropdown />
          </div>
        </nav>

      </div>
    </header>

    <RouterView />
    <button id="floating-map-button">
      <img width="40" src="@/assets/Message.png" alt="message icon">
    </button>

  </div>
  <footer>
    <FooterBox />
  </footer>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { RouterLink, RouterView, useRouter } from 'vue-router'
import MypageDropdown from './components/MypageDropdown.vue';
import FooterBox from './components/FooterBox.vue';
import MontagePopup from './components/MontagePopup.vue';
import AlarmModal from './components/AlarmModal.vue';

const router = useRouter();
const goToLogin = () => {
  router.push({ name: 'login' });
};

onMounted(() => {
  // 페이지가 마운트되면 모달을 보여주는 코드
  const modalElement = document.getElementById('exampleModal');
  const modalInstance = new bootstrap.Modal(modalElement, {
    keyboard: false
  });
  modalInstance.show();
  window.onYouTubeIframeAPIReady = () => {
    new YT.Player('player', {
      height: '530',
      width: '1120',
      videoId: 'p1', // YouTube 비디오 ID
      playerVars: {
        autoplay: 1, // 자동 재생 활성화
        mute: 1 // 자동 재생 정책에 따라 음소거 필요
      }
    });
  };
});

</script>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

nav {
  width: 100%;
  font-size: 20px;
  text-align: left;
  margin-top: 2rem;
  display: flex;
  align-items: center;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
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
  nav {
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
  color: rgb(39, 16, 171);
  gap: 20px;
  cursor: pointer;
  z-index: 10;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}
</style>
