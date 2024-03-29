<template>
  <div class="montagemainpage">
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <div class="accordion-header">
          <!-- 좋아요 버튼 -->
          <button class="like-btn" :class="{ liked: isLiked }" @click.stop="toggleLike()">
            <img v-if="isLiked" src="@/assets/icons/like-filled.png" alt="Liked">
            <img v-else src="@/assets/icons/like-outline.png" alt="Like">
          </button>
          <!-- 댓글 펼치기 -->
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
            댓글 5개 펼치기
          </button>
          <!-- 댓글 작성 인풋-->
          <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="댓글 쓰기" aria-label="Recipient's username"
              aria-describedby="button-addon2" v-model="addComment">
            <button class="btn btn-outline-secondary" type="button" id="button-addon2"
              @click="uploadComment">작성</button>
          </div>
        </div>

        <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
          <div class="accordion-body">
            <div class="list-group list-group-flush border-bottom scrollarea">
              <!-- 댓글 리스트 -->
              <button class="list-group-item list-group-item-action py-3 lh-tight" v-for="(comment, index) in comments"
                :key="index" @click.prevent="selectComment(comment)">
                <div class="d-flex w-100 align-items-center justify-content-between reply-block">
                  <strong class="mb-1" id="reply-member">{{ comment.memberId }}</strong>
                  <div class="col-10 mb-1 small comment-text">{{ comment.content }}</div>
                </div>
                 <!-- 대댓글 작성 인풋 -->
                 <div v-if="selectedComment === comment" class="mt-4 reply-container" @click.stop>
                  <input type="text" class="form-control reply-input" placeholder="대댓글 작성"
                    aria-label="Recipient's username" aria-describedby="reply-button" v-model="addReply">
                  <button class="btn btn-outline-secondary reply-btn" type="button" id="reply-button"
                    @click="uploadReply">작성</button>
                </div>
                <!-- 대댓글 리스트 -->
                <div v-for="(reply, rIndex) in comment.reply" :key="`reply-${rIndex}`" class="mt-2" id="reply">
                  <p>ㄴ <b id="reply-member">{{ reply.memberId }}</b> {{ reply.content }}</p>
                </div>
              
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>



<script setup>

import { onMounted, ref } from 'vue';
import axios from 'axios';

// 몽타쥬 좋아요
const isLiked = ref(false);
const toggleLike = () => {
  isLiked.value = !isLiked.value;
};

// 댓글 저장
const comments = ref([]);

// 댓글/대댓글 가져오는 메서드
const getCommentReply = () => {
  axios.get(`http://localhost:8080/api/montage/1/comment`)
    .then((response) => {
      console.log(response.data.data);
      comments.value = response.data.data;
    })
    .catch((error) => {
      console.error(error);
    });
};

// 일단 페이지 로드 시 실행
onMounted(() => {
  getCommentReply();
});

// 댓글 추가하기
const addComment = ref("");
const uploadComment = () => {
  const content = {
    "montageId": 1,
    "content": addComment.value,
    "isDeleted": false
  };
  axios
    .post(`http://localhost:8080/api/montage/1/comment`, content)
    .then((response) => {
      console.log(response.data);
      addComment.value = "";
      getCommentReply();
    })
    .catch((error) => {
      console.error(error);
    });
};


// 댓글 선택
const selectedComment = ref(null);
function selectComment(comment) {
  if (selectedComment.value === comment) {
    selectedComment.value = null;
  } else {
    selectedComment.value = comment;
  }
}

// 대댓글 추가하기
const addReply = ref("");
const uploadReply = () => {
  if (!selectedComment.value) {
    alert("댓글을 선택해주세요.");
    return;
  }
  const content = {
    "montageId": 1,
    "parentId": 1,
    "content": addReply.value,
    "isDeleted": false
  };
  axios
    .post(`http://localhost:8080/api/montage/${selectedComment.value.commentId}/comment`, content)
    .then((response) => {
      console.log(response.data);
      addReply.value = "";
    })
    .catch((error) => {
      console.error(error);
    });
};

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
  width: 25%;
}

.like-btn {
  background-color: white;
  border: none;
}

.like-btn img {
  width: 30px;
  height: 30px;
}

.accordion-item {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: 100%;
}

#reply {
  margin-left: 200px;
}

#addreply {
  margin-left: 200px;
  width: auto;
}

.reply-container {
  display: flex;
  margin-left: 200px;
  align-items: center;
  width: calc(100% - 200px);
}

.reply-input {
  flex-grow: 1;
  margin-right: 8px;
}

.reply-btn {
  white-space: nowrap;
  min-width: 80px;
}

#reply-member {
  display: inline-block;
  min-width: 5rem;
  max-width: 5rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.comment-text {
  font-size: 1em;
  /* 또는 원하는 크기로 조정 */
}
</style>