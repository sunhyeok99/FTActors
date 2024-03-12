<template>
  <!-- <h3><b>배우 프로필</b></h3> -->
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
    <div class="col" @click="goToProfileDetail">
      <div class="card">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">한재훈</h5>
          <p class="card-text">25세</p>
        </div>
      </div>
    </div>
  </div>


</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const goToProfileDetail = () => {
  router.push({ name: 'profileDetail' });
};
</script>
