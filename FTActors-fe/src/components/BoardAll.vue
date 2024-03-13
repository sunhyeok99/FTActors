<template>
  <!-- <h3><b>진행중인 공고</b></h3> -->
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="(board, index) in boards" :key="index" @click="goToBoardDetail(board.id)">
      <div class="card" id="board">
        <img src="../assets/board/공고1.jpg" class="card-img-top" alt="사진 업로드 실패">
        <!-- <img :src="board.image" class="card-img-top" alt="사진 업로드 실패"> -->
        <div class="card-body">
          <h5 class="card-title"><b>{{ board.title }}</b></h5>
          <p class="card-text">{{ board.deadline }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const boards = [
  { id: 1, title: "웹드라마 '씨타입' 채널에서 배우님을 모십니다", image: "../assets/board/공고1.jpg", deadline: "D-9 / 2024-03-15 마감" },
  { id: 2, title: "배우님을 모십니다", image: "../assets/board/공고2.jpg", deadline: "D-6 / 2024-03-12 마감" },
  { id: 3, title: "웹드라마 '씨타입' 채널에서 배우님을 모십니다", image: "../assets/board/공고1.jpg", deadline: "D-9 / 2024-03-15 마감" },
  { id: 4, title: "배우님을 모십니다", image: "../assets/board/공고2.jpg", deadline: "D-6 / 2024-03-12 마감" },
  { id: 5, title: "웹드라마 '씨타입' 채널에서 배우님을 모십니다", image: "../assets/board/공고1.jpg", deadline: "D-9 / 2024-03-15 마감" },
  { id: 6, title: "배우님을 모십니다", image: "../assets/board/공고2.jpg", deadline: "D-6 / 2024-03-12 마감" },
  { id: 7, title: "웹드라마 '씨타입' 채널에서 배우님을 모십니다", image: "../assets/board/공고1.jpg", deadline: "D-9 / 2024-03-15 마감" },
  { id: 8, title: "배우님을 모십니다", image: "../assets/board/공고2.jpg", deadline: "D-6 / 2024-03-12 마감" },
];

const goToBoardDetail = (boardId) => {
  router.push({ name: 'boardDetail', params: { id: boardId } });
};
</script>
<style>
#board {
  border: none;
}
</style>