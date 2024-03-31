<template>
  <div class="commentreplycomponent">
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <div class="accordion-header">
          <div class="columnthings">
            <div class="rowthings">
              <!-- 좋아요 버튼 -->
              <button class="like-btn" :class="{ liked: isLiked }" @click.stop="toggleLike()">
                <img v-if="isLiked" src="@/assets/icons/like-filled.png" alt="Liked">
                <img v-else src="@/assets/icons/like-outline.png" alt="Unliked">
              </button>
              <!-- 댓글 펼치기 -->
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne" id="comment-count">
                댓글 {{ comments.length }}개
              </button>
            </div>
            <!-- 댓글 작성 인풋-->
            <div class="input-group mb-3">
              <input type="text" class="form-control" placeholder="댓글 쓰기" aria-label="Recipient's username"
                aria-describedby="button-addon2" v-model="addComment">
              <button class="btn btn-outline-secondary" type="button" id="button-addon2"
                @click="uploadComment">작성</button>
            </div>
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
                  <div class="remove"><img src="@/assets/icons/Remove.png" alt=""
                      @click="deleteComment(comment.commentId)"></div>
                </div>
                <!-- 대댓글 작성 인풋 -->
                <div v-if="selectedComment === comment" class="mt-4 reply-container" @click.stop>
                  <input type="text" class="form-control reply-input" placeholder="대댓글 작성"
                    aria-label="Recipient's username" aria-describedby="reply-button" v-model="addReply">
                  <button class="btn btn-outline-secondary reply-btn" type="button" id="reply-button"
                    @click="uploadReply()">작성</button>
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
  if (addComment.value.trim().length > 0) {
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

    console.log("댓글이 작성되었습니다:", addComment);
    addComment.value = "";
  } else {
    console.log("댓글을 입력해주세요.");
  }

};

const deleteComment = (id) => {
  axios
    .delete(`http://localhost:8080/api/montage/1/comment/${id}`)
    .then(() => {
      console.log("댓글이 삭제되었습니다:");
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
    "parentId": selectedComment.value.commentId,
    "content": addReply.value,
    "isDeleted": false
  };
  axios
    .post(`http://localhost:8080/api/montage/${selectedComment.value.commentId}/comment`, content)
    .then((response) => {
      console.log(response.data);
      addReply.value = "";
      getCommentReply();
    })
    .catch((error) => {
      console.error(error);
    });
};

</script>

<style scoped>
.commentreplycomponent {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

/* 스크롤을 적용할 부분에만 해당하는 스타일 */
.accordion-body {
  max-height: 50vh; /* 뷰포트 높이의 일정 비율을 최대 높이로 설정 */
  overflow-y: auto; /* 세로 스크롤바가 필요할 때만 나타나도록 설정 */
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

.columnthings {
  display: flex;
  flex-direction: column;
}
.rowthings{
  display: flex;
  flex-direction: row;
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

p {
  margin-bottom: auto;
}

#reply {
  margin-left: 20px;
}

#addreply {
  margin-left: 20px;
  width: auto;
}

.remove img {
  width: 15px;
  height: 15px;
}

.reply-container {
  display: flex;
  margin-left: 20px;
  align-items: center;
  width: calc(100% - 20px);
}

.reply-btn {
  min-width: 80px;
}

.reply-block {
  display: flex;
}

#reply-member {
  display: inline-block;
  margin-left: 10px;
  min-width: 2rem;
  max-width: 2rem;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.comment-text {
  font-size: 1em;
  /* 또는 원하는 크기로 조정 */
}

#comment-count{
  min-width: 150px;
}
</style>