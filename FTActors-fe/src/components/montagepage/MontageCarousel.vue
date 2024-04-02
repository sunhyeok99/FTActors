<template>
 
    <div id="carouselExampleAutoplaying" class="carousel slide w-75 m-auto" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item" v-for="montage in montages" :key="montage.id">
          <div class="col">
            <div class="card montage" @click="goToMontageDetail(montage.id)">
              <video :src="montage.link" muted autoplay playsinline></video>
              <div class="montage-title">{{ montage.title }}</div>
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

const router = useRouter();
const montages = ref([]);

// 몽타쥬 리스트 가져오는 메서드
const getMontages = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/montage/list`);
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