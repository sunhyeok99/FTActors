<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="board in boards" :key="board.id">
      <div class="card" id="board">
        <img :src= "board.image" alt="" @click="goToBoardDetail(board.id)">
        <button class="like-btn" @click ="toggleLike(board.id)">
          <img v-if="board.wishList === 1" src="@/assets/icons/like-filled.png" alt="Liked">
          <img v-else src="@/assets/icons/like-outline.png" alt="Like">         
        </button>
        <div class="card-body" @click="goToBoardDetail(board.id)">
          <h5 class="card-title"><b>{{ board.title }}</b></h5>
          <p class="card-text">{{ board.content }}</p>
          <p class="card-text">종료일자: {{ board.endDate }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { recruitmentApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";
  
const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
const adminId = 11;

const router = useRouter();
const boards = ref([]);

// getList 함수 정의: 백엔드로부터 공고 리스트를 받아오는 함수
const getList = async (memberId) => {
  try {
    await recruitmentApi.getList(memberId).then((res) => {
      boards.value = res.data.data;
    })   
  } catch (error) {
    console.error('Error fetching recruitment list:', error);
  }
};
const goToBoardDetail = (boardId) => {
  router.push({ name: 'boardDetail', params: { id: boardId } });
};
const toggleLike = async (index) => {
  try {
    if(loginMember.value =="" || loginMember.value == null){
      alert(" 로그인이 필요합니다")
      router.push({ name: 'login' });
    }
    else{
      const memberId = loginMember.value; 
      const recruitmentId = index;
      const response = await recruitmentApi.updateWishlist(recruitmentId, memberId);
      if(response.data.status == 200){
        if(response.data.data == 0){
          alert('찜 목록에서 삭제되었습니다.')
        }
        else{
          alert('찜 등록이 완료되었습니다.')
        }
        const tmp = boards.value.find(board => board.id === index);
        tmp.wishList = response.data.data;
        console.log(tmp.wishList)
      }
    }
    } catch (error) {
      console.error('Error toggling like:', error);
  }
};

// 페이지가 로드될 때 getList 함수 호출
onMounted(() => {
  if(loginMember.value == "" || loginMember.value == null){
    getList(adminId);
  }
  else{
    getList(loginMember.value);
  }
});



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
