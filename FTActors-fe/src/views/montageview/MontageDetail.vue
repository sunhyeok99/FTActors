<template>
  <div class="montagedetail">
    <div class="montage" ref="thumbnail">
      <img src="@/assets/icons/Next.png" alt="" id="previous" @click="goToPreviousMontage">
      <video :src="montage.link" muted autoplay playsinline></video>
      <img src="@/assets/icons/Next.png" alt="" id="next" @click="goToNextMontage">
    </div>
    <div class="commentreply">
      <CommentReply/>
    </div>
  </div>
</template>
<script setup>
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import { watch, onMounted, ref } from 'vue';
import CommentReply from '@/components/common/CommentReply.vue'

const router = useRouter();
const route = useRoute();
const thumbnail = ref(null);
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
  if (currentId.value > 0) {
    const previousId = currentId.value - 1;
    router.push({ name: 'montageDetail', params: { id: previousId } });
    getMontage();
  } else {
    alert('이전 컨텐츠가 없습니다.');
  };
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
<style scoped>
.montagedetail {
  display: flex;
  padding: 0.5rem;
  width: 100%;
}

.montage {
  align-items: center;
}

.montage video {
  height: auto;
  width: 50vw;
  z-index: 2;
  flex: 3;
}

.montage img {
  width: 25px;
  height: 25px;
  margin: 2rem;
}
.commentreply {
  flex:3;
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