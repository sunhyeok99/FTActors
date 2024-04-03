<template>
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col" v-for="apply in applys" :key="apply.id">
        <div class="card" id="apply">
          <div class="card-body" @click="goToApplyDetail(apply.id)">
            <h5 class="card-title"><b>{{ apply.recruitmentTitle }}</b></h5>
            <p class="card-text">{{ apply.content }}</p>
            <p class="card-text">지원일자: {{ apply.createdAt }}</p>
          </div>
        </div>
      </div>
      <button @click="goToEditPage()">영상 편집</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRouter } from 'vue-router';
  import { recruitmentApi } from '@/util/axios';
  import { useMemberStore } from "@/stores/member-store.js";
    
  const router = useRouter();
  const MemberStore = useMemberStore();
  const loginMember = ref(null);
  loginMember.value = MemberStore.memberInfo;
  const recruitmentId = router.currentRoute.value.params.id;
  
  const applys = ref([]);
  
  // getList 함수 정의: 백엔드로부터 공고 리스트를 받아오는 함수
  const getList = async (recruitmentId) => {
    try {
      await recruitmentApi.getApplyList(recruitmentId).then((res) => {
        applys.value = res.data.data;
      })   
    } catch (error) {
      console.error('Error fetching recruitment list:', error);
    }
  };
  
  const goToApplyDetail = (applyId) => {
  router.push({ name: 'applyDetail', params: { id: applyId } });
};
  
  // 페이지가 로드될 때 getList 함수 호출
  onMounted(() => {
      getList(recruitmentId);
  });
  const goToEditPage = () => {
  router.push({ name: 'mainVideo', params: { id : recruitmentId } });
};

  
  
  </script>
  
  <style scoped>
  #board {
    border: none;
  }
  
  .image-container {
    position: relative;
    display: inline-block;
  }
  
  .card-img-top {
    width: 100%;
    /* Adjust the size as needed */
    display: block;
  }
  
  .like-btn {
    position: absolute;
    top: 10px;
    right: 10px;
    background: transparent;
    border: none;
    cursor: pointer;
  }
  
  .liked img {
    filter: invert(36%) sepia(94%) saturate(3013%) hue-rotate(346deg) brightness(100%) contrast(97%);
  }
  
  .like-btn img {
    width: 20px;
    height: 20px;
  }
  </style>
  