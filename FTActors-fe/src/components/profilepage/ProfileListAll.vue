<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="profile in profiles" :key="profile.id">
      <div class="card" id="profile" @click="goToProfileDetail(profile.id)">
        <img :src="profile.imageLink" alt="사진 업로드 실패">
        <div class="card-body">
          <h5 class="card-title"><b>{{ getProfileTitle(profile) }}</b></h5>
          <p class="card-text">{{ getAge(profile.birth) }}세</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { profileApi } from '@/util/axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const profiles = ref([]);

const goToProfileDetail = (profileId) => {
  router.push({ name: 'profileDetail', params: { id: profileId } });
};

const getList = async (sort) => {
  try {
    const res = await profileApi.getProfileList();
    profiles.value = res.data.data;
  } catch (error) {
    console.error('Error fetching recruitment list:', error);
  }
};

onMounted(() => {
  getList();
});

const getProfileTitle = (profile) => {
  return profile.type === 'A' ? '배우 ' +  profile.name : '감독 ' + profile.name;
};

const getAge = (birth) => {
  const birthDate = new Date(birth);
  const currentDate = new Date();
  const age = currentDate.getFullYear() - birthDate.getFullYear();
  return age;
};
</script>

<style scoped>
.image-container {
  position: relative;
  display: inline-block;
}

.card-img-top {
  width: 100%;
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

.like-btn img {
  width: 30px;
  height: 30px;
}
</style>
