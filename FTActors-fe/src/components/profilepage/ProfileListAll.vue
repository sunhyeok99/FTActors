<!-- <template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="(profile, index) in profiles" :key="index" @click="goToProfileDetail(profile.id)">
      <div class="card" id="profile">
        <img src="@/assets/actors/김지원.jpg" class="card-img-top" alt="사진 업로드 실패">
        <img :src="profile.image" class="card-img-top" alt="사진 업로드 실패">
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
</script> -->

<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="(profile, index) in profiles" :key="index">
      <div class="card" id="profile" @click="goToProfileDetail(profile.id)">
        <img src="@/assets/actors/소희.jpg" alt="사진 업로드 실패">
        <button class="like-btn" :class="{ liked: profile.isLiked }" @click.stop="toggleLike(index)">
          <img v-if="profile.isLiked" src="@/assets/icons/like-filled.png" alt="Liked">
          <img v-else src="@/assets/icons/like-outline.png" alt="Like">
        </button>
        <div class="card-body">
          <h5 class="card-title"><b>{{ profile.name }}</b></h5>
          <p class="card-text">{{ profile.age }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router'

const router = useRouter();
const profiles = reactive([
  { id: 1, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
  { id: 2, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
  { id: 3, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
  { id: 4, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
  { id: 5, name: "김지원", image: "../assets/actors/김지원.jpg", age: "25세" },
]);

const goToProfileDetail = (profileId) => {
  router.push({ name: 'profileDetail', params: { id: profileId } });
};

const toggleLike = (index) => {
  profiles[index].isLiked = !profiles[index].isLiked;
};
</script>

<style scoped>


.image-container {
  position: relative;
  display: inline-block;
}

.card-img-top {
  width: 100%;
  /* Adjust the size as needed */
  display: block;
}

.like-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: transparent;
  border: none;
  cursor: pointer;
}

.liked img {
  filter: invert(36%) sepia(94%) saturate(3013%) hue-rotate(346deg) brightness(100%) contrast(97%);
}

.like-btn img {
  width: 20px;
  height: 20px;
}
</style>
