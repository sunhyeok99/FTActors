<template>
  <div class="profilepage">
  <div class="title">
    <h3><b>배우 프로필</b></h3>
    <button type="button" class="btn pageright" @click="goToProfilePage">▶️더보기</button>
  </div>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="profile in profiles" :key="profile.id">
      <div class="card" id="profile" @click="goToProfileDetail(profile.id)">
         <img :src= profile.imageLink class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title"><b>{{ getProfileTitle(profile) }}</b></h5>
          <p class="card-text">{{ getAge(profile.birth) }}세</p>
        </div>
      </div>
    </div>

  </div>
</div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { profileApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";

const profiles = ref({});

const goToProfileDetail = (profileId) => {
  router.push({ name: 'profileDetail', params: { id: profileId } });
};
const getList = async () => {
  try {
    await profileApi.getProfileList().then((res) => {
      console.log(res.data.data)
      profiles.value = res.data.data;
    })   
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

const router = useRouter();
const goToProfilePage = () => {
  router.push({ name: 'profile' });
};
</script>

<style scoped> 
.title {
  display: flex;  
}
.profilepage {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.pageright {
  display: flex;
  margin-left: auto;
}
</style>