<template>
  <div class="montagemainpage">
    <!-- 좋아요 댓글 행 -->
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <div class="accordion-header">
          <!-- 좋아요 토글 버튼 -->
          <button class="like-btn" :class="{ liked: isLiked }" @click.stop="toggleLike()">
            <img v-if="isLiked" src="@/assets/icons/like-filled.png" alt="Liked">
            <img v-else src="@/assets/icons/like-outline.png" alt="Like">
          </button>
          <!-- 댓글 토글 버튼-->
          
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
            댓글 5개 펼치기
          </button>
       
        <!-- 댓글 작성 -->
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="댓글 쓰기" aria-label="Recipient's username"
            aria-describedby="button-addon2">
          <button class="btn btn-outline-secondary" type="button" id="button-addon2">작성</button>
        </div>
        </div>
        <!-- 댓글 표시 -->
        <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
          <div class="accordion-body">
            <div class="list-group list-group-flush border-bottom scrollarea">
              <a href="#" class="list-group-item list-group-item-action py-3 lh-tight"
                v-for="(comment, index) in comments" :key="index" @click.prevent="selectComment(comment)">
                <div class="d-flex w-100 align-items-center justify-content-between">
                  <strong class="mb-1">{{ comment.memberId }}</strong>
                  <div class="col-10 mb-1 small">{{ comment.content }}</div>
                </div>
                <!-- 대댓글 -->
                <div v-if="selectedComment" class="mt-4">
                  <!-- 대댓글 작성 -->
                  <input type="text" class="form-control" placeholder="대댓글 쓰기" aria-label="Recipient's username"
                    aria-describedby="button-addon2">
                  <button class="btn btn-outline-secondary" type="button" id="button-addon2">작성</button>
                  <!-- 대댓글 표시 -->
                  <p v-for="(r, index) in selectedComment" :key="index">ㄴ {{ r.content }}</p>
                </div>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

import { ref } from 'vue';

// 좋아요
const isLiked = ref(false)
const toggleLike = () => {
  isLiked.value = !isLiked.value;
};

// 댓글 선택하여 대댓글 확인
const selectedComment = ref(null);
const comments = ref([
  {
    memberId: 1,
    commentId: 1,
    content: '잘 부탁드립니다.',
    isDeleted: false,
    reply: [
      {
        commentId: 2,
        memberId: 1,
        content: "cupid",
        isDeleted: false,
        created_at: "2024-03-20T20:46:56",
        updated_at: "2024-03-20T20:46:56"
      },
      {
        commentId: 3,
        memberId: 2,
        content: "cupid",
        isDeleted: false,
        created_at: "2024-03-20T20:46:56",
        updated_at: "2024-03-20T20:46:56"
      },

    ]
  },
  {
    memberId: 1,
    commentId: 4,
    content: '감사합니다.',
    isDeleted: false,
    reply: [
      {
        commentId: 5,
        memberId: 1,
        content: "cupid",
        isDeleted: false,
        created_at: "2024-03-20T20:46:56",
        updated_at: "2024-03-20T20:46:56"
      },
      {
        commentId: 6,
        memberId: 2,
        content: "cupid",
        isDeleted: false,
        created_at: "2024-03-20T20:46:56",
        updated_at: "2024-03-20T20:46:56"
      },

    ]
  },
]);
// 선택된 댓글
function selectComment(comment) {
  if (selectedComment.value === comment.reply) {
    selectedComment.value = null;
    selectedComment.value = comment.reply;
  }
}




</script>

<style scoped>
.montagemainpage {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.accordion {
  display: flex;
}

.accordion-header {
  display: flex;
  flex-direction: row;
}

.accordion-button {
  margin: 1rem;
}

.like-btn {
  background-color: white;
  border: none;
}
.like-btn img{
  width: 30px;
  height: 30px;
}

.accordion-item {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
}


.accordion-button {
  justify-content: right;
}
</style>