<template>
  <div class="row row-cols-1 row-cols-md-1">
    <div class="col" v-for="montage in montages" :key="montage.id" @click="goToMontageDetail(montage.id)">
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
import { useMemberStore, useJwtStore } from "@/stores/member-store.js";
  
const JWTStore = useJwtStore();
  const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
const router = useRouter();

const montages = ref([]);

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