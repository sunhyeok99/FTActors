<template>
    <div class="dropdown">

        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
        <img src="@/assets/icons/DefaultProfile.png" alt="">
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="#" @click="goToLogout">로그아웃</a></li>
          <li><a class="dropdown-item" href="#" @click="goToMypage">마이페이지</a></li>
        </ul>
      </div>
</template>

<script setup>
import {  useRouter } from 'vue-router'
const router = useRouter();
const goToMypage = () => {
  router.push({ name: 'mypage' });
};

const goToLogout = () => {
  router.push({ name: 'logout' });
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