<template>
  <div class="wrapper">
  <header>
    <div>
      <nav>
        <RouterLink to="/" id="name">배우는 사람</RouterLink>
        <RouterLink to="/board">진행중인 공고</RouterLink>
        <RouterLink to="/profile">배우 프로필</RouterLink>
        <RouterLink to="/montage">몽타쥬</RouterLink>
        <div class="pageright">
          <img src="@/assets/Alarm.png" alt="">
        <button type="button" class="btn btn-dark" id="loginbtn" @click="goToLogin">로그인</button>
        <MypageDropdown />
      </div>
      </nav>
      
    </div>
  </header>

  <RouterView />
   <button id="floating-map-button">
        <img width="20" src="@/assets/Message.png" alt="map icon" >
    </button>
</div>
  <footer>
    <FooterBox /> 
  </footer>
</template>

<script setup>
import { RouterLink, RouterView, useRouter } from 'vue-router'
import MypageDropdown from './components/MypageDropdown.vue';
import FooterBox from './components/FooterBox.vue';

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

nav {
  width: 100%;
  font-size: 20px;
  text-align: left;
  margin-top: 2rem;
  display: flex;
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
  border: none; /* 기존 border 설정을 수정 */
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
.pageright img{
  width: 24px;
  height: 24px;
}
.wrapper {
  padding: 2rem;
}
#floating-map-button {
  position: fixed;
  display: flex;
  padding: 14px 19px;
  align-items:center;
  left: 90%;
  bottom: 10%;
  background-color: lightgrey;
  border-radius: 50%;
  transform: translateX(-50%);
  color: black;
  gap: 8px;
  cursor: pointer;

}
</style>
