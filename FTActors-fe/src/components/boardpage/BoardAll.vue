<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="(board, index) in boards" :key="index">
      <div class="card" id="board" @click="goToBoardDetail(board.id)">
        <img src="@/assets/board/b3.jpg" alt="">
        <button class="like-btn" :class="{ liked: board.isLiked }" @click.stop="toggleLike(index)">
          <img v-if="board.isLiked" src="@/assets/icons/like-filled.png" alt="Liked">
          <img v-else src="@/assets/icons/like-outline.png" alt="Like">
          <!-- <div v-if="board.isLiked"><i class="bi bi-star-fill"></i></div>
          <div v-else><i class="bi bi-star"></i></div> -->
         
        </button>
        <div class="card-body">
          <h5 class="card-title"><b>{{ board.title }}</b></h5>
          <p class="card-text">{{ board.deadline }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router'

const router = useRouter();
const boards = reactive([
  { id: 1, title: "웹드라마 '씨타입' 채널에서 배우님을 모십니다", image: "@/assets/board/b1.jpg", deadline: "D-9 / 2024-03-15 마감", isLiked: false },
  { id: 2, title: "웹드라마 '씨타입' 채널에서 배우님을 모십니다", image: "@/assets/board/b1.jpg", deadline: "D-9 / 2024-03-15 마감", isLiked: false },
  { id: 3, title: "웹드라마 '씨타입' 채널에서 배우님을 모십니다", image: "@/assets/board/b1.jpg", deadline: "D-9 / 2024-03-15 마감", isLiked: false },
  { id: 4, title: "웹드라마 '씨타입' 채널에서 배우님을 모십니다", image: "@/assets/board/b1.jpg", deadline: "D-9 / 2024-03-15 마감", isLiked: false },

  // 이하 생략
]);

const goToBoardDetail = (boardId) => {
  router.push({ name: 'boardDetail', params: { id: boardId } });
};

// `toggleLike` 함수 수정: 특정 인덱스의 좋아요 상태를 토글
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
