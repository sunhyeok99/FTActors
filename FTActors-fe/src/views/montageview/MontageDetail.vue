<template>
  <div class="montagepage">
    <div class="montagethumbnail">
      <img src="@/assets/icons/Next.png" alt="" id="previous" @click="goToPreviousMontage">
      <video :src="montage.link" muted autoplay playsinline></video>
      <img src="@/assets/icons/Next.png" alt=""id="next" @click="goToNextMontage">
    </div>
    <div class="montagedetail">
    </div>
  </div>
</template>
<script setup>
import { useRouter, useRoute } from 'vue-router';
import {onMounted, ref} from 'vue';
import axios from 'axios';
import { watch } from 'vue';

const router = useRouter();
const route = useRoute();

const currentId = ref(parseInt(route.params.id, 10));

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
.montagepage {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
}

.montagethumbnail {
  display: flex;
  align-items: center;
  margin-bottom: 4rem;
}

.montagethumbnail img {
  width: 25px;
  height: 25px;
  margin: 2rem;
}

.montagedetail {
  width: 30rem;
}

#previous {
  transform: rotate(180deg);
  color-interpolation-filters : white;
  cursor: pointer;
}
#next {
  cursor: pointer;
}

</style>