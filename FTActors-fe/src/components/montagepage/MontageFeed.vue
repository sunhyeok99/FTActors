<template>
  <div class="row row-cols-1 row-cols-md-1">
    <div class="col" v-for="(montage, index) in montages" :key="index" @click="goToMontageDetail(index)">
      <div class="card montage">
        <video :src="montage.link" muted autoplay playsinline></video>
        <div class="montage-title">{{ montage.title }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from 'axios';

const router = useRouter();
const montages = ref([]);
const offcanvas = ref(null);

// 몽타쥬리스트 가져오는 메서드
const getMontages = () => {
  axios.get(`http://localhost:8080/montage/list`)
    .then((response) => {
      console.log(response.data.data);
      montages.value = response.data.data;
    })
    .catch((error) => {
      console.error(error);
    });
};


// 일단 페이지 로드 시 실행
onMounted(() => {
  getMontages();
});

const goToMontageDetail = (montageId) => {
  router.push({ name: 'montageDetail' , params: { id: montageId }});
  const bsOffcanvas = bootstrap.Offcanvas.getInstance(offcanvas.value);
  bsOffcanvas.hide();

};

</script>
<style setup>

.montage {
  border-radius: 0;
  border:0;
  background-color: transparent;
  --bs-card-bg:none;
  color:white;
  
}
.card{
  display: flex;
}
.col{
  padding: 0;
}
.montage-title {
  position: absolute;
  top: -5%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: rgb(194, 194, 194); /* 필요한 색상으로 설정 */
}
</style>