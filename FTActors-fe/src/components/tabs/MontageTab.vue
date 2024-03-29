
<template>
  <div class="wrapper">
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="(montage, index) in myMontages" :key="index" @click="goToMontageDetail(index)">
      <div class="card montage">
        <video :src="montage.link" muted autoplay playsinline></video>
        <div class="montage-title">{{ montage.title }}</div>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>

import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from 'axios';

const router = useRouter();
const myMontages = ref([]);

// 몽타쥬리스트 가져오는 메서드
const getMyMontages = () => {
  axios.get(`http://localhost:8080/api/montage/my-montage`)
    .then((response) => {
      console.log(response.data.data);
      myMontages.value = response.data.data;
    })
    .catch((error) => {
      console.error(error);
    });
};


// 일단 페이지 로드 시 실행
onMounted(() => {
  getMyMontages();
});


const goToMontageDetail = (montageId) => {
  router.push({ name: 'montageDetail' , params: { id: montageId }});


};

</script>

<style>
.wrapper {
  padding-top: 2rem;
  padding-bottom: 2rem;
}
.col {
  padding: 1rem;
}
</style>