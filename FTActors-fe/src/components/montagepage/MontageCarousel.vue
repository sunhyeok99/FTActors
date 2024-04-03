<template>
 <div>
    <div id="carouselExampleAutoplaying" class="carousel slide w-75 m-auto" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item" v-for="(montage, index) in montages" :key="index" :class="{ active: index === 0 }">
          <div class="col">
            <div class="card montage" @click="goToMontageDetail(index)">
              <video :src="montage.link" muted autoplay playsinline></video>
              <div class="montage-title">
                <strong> {{ montage.title }} {{ formatDate(montage.created_at) }} </strong> </div>
            </div>
            </div>
          </div>
         
        </div>
      </div>
    </div>
   
 
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from 'axios';

const SERVER_URL = 'https://j10a602.p.ssafy.io/api/'
const router = useRouter();
const montages = ref([]);

// 몽타쥬 리스트 가져오는 메서드
const getMontages = async () => {
  try {
    const response = await axios.get(`${SERVER_URL}/montage/list`);
    montages.value = response.data.data;
    console.log(response.data.data);
  } catch (error) {
    console.error(error);
  }
};

onMounted(() => {
  getMontages();
});

const goToMontageDetail = (montageId) => {
  router.push({ name: 'montageDetail', params: { id: montageId } });
};
const formatDate = (dateStr) => {
      const date = new Date(dateStr);
      const year = date.getFullYear().toString().substr(-2); 
      const month = ('0' + (date.getMonth() + 1)).slice(-2); 
      const day = ('0' + date.getDate()).slice(-2); 
      return `${year}${month}${day}`;
}
</script>

<style scoped>
.carousel-item video {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
}

.carousel-inner {
  width: 100%;
  height: 100%;
}
</style>