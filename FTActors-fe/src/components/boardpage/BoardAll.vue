<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="board in boards" :key="board.id">
      <div class="card" id="board" @click="goToBoardDetail(board.id)">
        <img :src= "board.image" alt="">
        <button class="like-btn" :class="{ liked: board.isLiked }" @click.stop="toggleLike(index)">
          <img v-if="board.wishList" src="@/assets/icons/like-filled.png" alt="Liked">
          <img v-else src="@/assets/icons/like-outline.png" alt="Like">         
        </button>
        <div class="card-body">
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
import axios from 'axios';
import { recruitmentApi } from '@/util/axios';

const router = useRouter();
const boards = ref([]);

// getList 함수 정의: 백엔드로부터 공고 리스트를 받아오는 함수
const getList = async (memberId) => {
  try {
    await recruitmentApi.getList(1).then((res) => {
      boards.value = res.data.data;
    })   
  } catch (error) {
    console.error('Error fetching recruitment list:', error);
  }
};

// 페이지가 로드될 때 getList 함수 호출
onMounted(() => {
  getList(1); 
});

const goToBoardDetail = (boardId) => {
  router.push({ name: 'boardDetail', params: { id: boardId } });
};

const toggleLike = (index) => {
  boards[index].isLiked = !boards[index].isLiked;
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
