<template>
  <div class="montagepage">
    <div class="overlay" v-if="isVideoPlaying"></div>
    <div class="montagethumbnail" ref="thumbnail">
      <img src="@/assets/icons/Next.png" alt="" id="previous" @click="goToPreviousMontage">
      <video :src="montage.link" muted autoplay playsinline @play="isVideoPlaying = true" @ended="isVideoPlaying = false"></video>
      <img src="@/assets/icons/Next.png" alt="" id="next" @click="goToNextMontage">
    </div>
    <div class="montagedetail">
    </div>
  </div>
</template>
<script setup>
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import { watch, onMounted, ref } from 'vue';

const router = useRouter();
const route = useRoute();
const thumbnail = ref(null);
const currentId = ref(parseInt(route.params.id, 10));
const isVideoPlaying = ref(false);
const getMontage = () => {
  axios.get(`http://localhost:8080/api/montage/list`)
    .then((response) => {
      if (currentId.value >= 0 && currentId.value < response.data.data.length) {
        montage.value = response.data.data.find((m, index) => index === currentId.value);
      } else {
        console.error('Invalid montage ID:', currentId.value);
      }
    })
    .catch((error) => {
      console.error(error);
    });
};

const goToPreviousMontage = () => {
  const previousId = currentId.value - 1;
  router.push({ name: 'montageDetail', params: { id: previousId } });
  getMontage();
};
const goToNextMontage = () => {
  const nextId = currentId.value + 1;
  router.push({ name: 'montageDetail', params: { id: nextId } });
  getMontage();
};

const montage = ref([]);
watch(() => route.params.id, (newId) => {
  currentId.value = parseInt(newId, 10);
  getMontage();
}, { immediate: true });



onMounted(() => {
  getMontage();
});


</script>
<style>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.9); 
  z-index: 1; 
}

.montagepage {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
}
.montagethumbnail {
  display: flex;
  align-items: center;
  justify-content: center; /* 가운데 정렬 */
  margin-bottom: 4rem;
  width: 100%; /* 컨테이너 전체 너비 */
}

.montagethumbnail video {
  height: 75vh; 
  width: auto;
  z-index: 2;
}

.montagethumbnail img {
  width: 25px;
  height: 25px;
  margin: 2rem;
  z-index: 2; 
}

.montagedetail {
  width: 30rem;
}
#previous {
  transform: rotate(180deg);
  color-interpolation-filters: white;
  cursor: pointer;
}
#next {
  cursor: pointer;
}
</style>