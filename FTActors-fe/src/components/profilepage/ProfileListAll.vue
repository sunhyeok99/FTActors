<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="profile in profiles" :key="profile.id">
      <div class="card" id="profile" @click="goToProfileDetail(profile.id)">
        <img :src="profile.imageLink" alt="사진 업로드 실패">
        <div class="card-body">
          <h5 class="card-title"><b>{{ profile.name }}</b></h5>
          <p class="card-text">{{ profile.age }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { profileApi } from '@/util/axios';
import { ref, reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router'

const router = useRouter();
const profiles = ref({});

const goToProfileDetail = (profileId) => {
  router.push({ name: 'profileDetail', params: { id: profileId } });
};

const getList = async (sort) => {
  try {
    await profileApi.getAllProfileList(sort).then((res) => {
      profiles.value = res.data.data;
    })   
  } catch (error) {
    console.error('Error fetching recruitment list:', error);
  }
};

onMounted(() => {
    getList(1);
});



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
