<script setup>
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <header>
    <div>
      <nav>
        <RouterLink to="/" id="name">배우는 사람</RouterLink>
        <RouterLink to="/board">진행중인 공고</RouterLink>
        <RouterLink to="/profile">배우 프로필</RouterLink>
        <RouterLink to="/montage">몽타쥬</RouterLink>
        <button type="button" class="btn btn-dark" id="loginbtn">로그인</button>
      </nav>
      
    </div>
  </header>
  <RouterView />
</template>

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
</style>
