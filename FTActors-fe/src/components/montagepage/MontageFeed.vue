<template>
  <div class="row row-cols-1 row-cols-md-1">
    <div class="col" v-for="(montage, index) in montages" :key="index" @click="goToMontageDetail(index)">
      <div v-if="index > 3" class="montage">
        <video crossorigin="anonymous" :src="montage.link" muted autoplay playsinline></video>
        <div class="montage-title">
          <strong> {{ montage.title }} {{ formatDate(montage.created_at) }} </strong> </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from 'axios';

const SERVER_URL = 'https://j10a602.p.ssafy.io/api';
const ERVER_URL = 'https://localhost:8080';
const router = useRouter();
const montages = ref([]);

// 몽타쥬리스트 가져오는 메서드
const getMontages = () => {
  axios.get(`${ERVER_URL}/montage/list`)
    .then((response) => {
      console.log(response.data.data);
      montages.value = response.data.data;
    })
    .catch((error) => {
      console.error(error);
    });
};

const formatDate = (dateStr) => {
      const date = new Date(dateStr);
      const year = date.getFullYear().toString().substr(-2); 
      const month = ('0' + (date.getMonth() + 1)).slice(-2); 
      const day = ('0' + date.getDate()).slice(-2); 
      return `${year}${month}${day}`;
}

// 일단 페이지 로드 시 실행
onMounted(() => {
  getMontages();
});

const goToMontageDetail = (montageId) => {
  router.push({ name: 'montageDetail' , params: { id: montageId }});
};


</script>
<style setup>

.montage {
  border-radius: 0;
  border: 0;
  background-color: transparent;
  --bs-card-bg: none;
  color: white;
  position: relative; /* 추가: 카드 내에서 절대 위치를 사용하기 위함 */
}

.card {
  display: flex;
}

.col {
  padding: 0;
}

.montage-title {
  position: absolute;
  bottom: 0; /* 상단 정렬에서의 위치 조정 */
  left: 0; /* 오른쪽 정렬을 위해 변경 */
  color: rgb(194, 194, 194); /* 필요한 색상으로 설정 */
  padding-right: 20px; /* 오른쪽 패딩 추가 */
}
</style>