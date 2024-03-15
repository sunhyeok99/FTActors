<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="(profile, index) in profiles" :key="index" @click="goToProfileDetail(profile.id)">
      <div class="card" id="profile">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="사진 업로드 실패">
        <!-- <img :src="profile.image" class="card-img-top" alt="사진 업로드 실패"> -->
        <div class="card-body">
          <h5 class="card-title"><b>{{ profile.name }}</b></h5>
          <p class="card-text">{{ profile.age }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>



import { useRouter } from 'vue-router'
const router = useRouter();
const profiles = [
  { id: 1, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
  { id: 2, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
  { id: 3, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
  { id: 4, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
  { id: 5, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
 
];

const goToProfileDetail = (profileId) => {
  router.push({ name: 'profileDetail' , params: { id: profileId }});
};
</script>
