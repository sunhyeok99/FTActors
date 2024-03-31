<template>
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col" v-for="apply in applys" :key="apply.id">
        <div class="card" id="apply" @click="goToApplyDetail(apply.id)">
          <div class="card-body">
            <h5 class="card-title"><b>{{ apply.recruitmentTitle }}</b></h5>
            <p class="card-text">{{ apply.content }}</p>
            <p class="card-text">지원 날짜: {{ apply.createdAt }}</p>
          </div>
        </div>
        <button @click="goToApplyDetail(apply.id)" class="btn-create">상세 보기</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, reactive, onMounted } from 'vue';
  import { useRouter } from 'vue-router';
  import axios from 'axios';
  import { recruitmentApi } from '@/util/axios';
  import { useMemberStore } from "@/stores/member-store.js";
  
  const MmeberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
  const router = useRouter();
  const applys = ref([]);
  
  // getList 함수 정의: 백엔드로부터 공고 리스트를 받아오는 함수
  const getList = async (memberId) => {
    try {
      await recruitmentApi.getMyApply(memberId).then((res) => {
        applys.value = res.data.data;
      })   
    } catch (error) {
      console.error('Error fetching recruitment list:', error);
    }
  };
  
  // 페이지가 로드될 때 getList 함수 호출
  onMounted(() => {
    getList(loginMember.id); 
  });
  
  const goToApplyDetail = (applyId) => {
    router.push({ name: 'applyDetail', params: { id: applyId } });
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
  