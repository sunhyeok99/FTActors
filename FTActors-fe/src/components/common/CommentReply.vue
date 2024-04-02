<template>
  <div class="commentreplycomponent">
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <div class="accordion-header">
          <div class="columnthings">
            <div class="rowthings">
              <!-- 좋아요 버튼 -->
              <button class="like-btn" :class="{ liked: isLiked }" @click.stop="toggleLike() ,addLike()">
                <img v-if="isLiked" src="@/assets/icons/like-filled.png" alt="Liked">
                <img v-else src="@/assets/icons/like-outline.png" alt="Unliked">
                <p>{{ montageInfo.likeCount }}</p>
              </button>
              <!-- 신고 버튼 -->
              <button type="button" class="report-btn" data-bs-toggle="modal" data-bs-target="#reportModal" >
                <img src="@/assets/icons/Scissors.png" alt="cut">
              </button>
              <ReportModal :current-id="currentId" />
              <!-- 댓글 펼치기 -->
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne"
                id="comment-count">
                댓글 {{ comments.length }}개
              </button>
            </div>
            <!-- 댓글 작성 인풋-->
            <div class="input-group mb-3">
              <input type="text" class="form-control" placeholder="댓글 쓰기" aria-label="Recipient's username"
                aria-describedby="button-addon2" v-model="addComment">
              <button class="btn btn-outline-secondary" type="button" id="button-addon2"
                @click.prevent="uploadComment">작성</button>
            </div>
          </div>
        </div>

        <transition name="slide">
          <div class="accordion-collapse collapse show" v-if="comments.length > 0">
            <div class="accordion-body">
              <div class="list-group list-group-flush border-bottom scrollarea">
                <!-- 댓글 리스트 -->
                <button class="list-group-item list-group-item-action py-3 lh-tight"
                  v-for="(comment, index) in comments" :key="index" @click.prevent="selectComment(comment)">
                  <div class="d-flex w-100 align-items-center justify-content-between reply-block">
                    <strong class="mb-1" id="reply-member">{{ comment.memberId }}</strong>
                    <div class="col-10 mb-1 small comment-text">{{ comment.content }}</div>
                    <!-- 삭제 버튼 -->
                    <div class="remove"><img src="@/assets/icons/Remove.png" alt=""
                        @click.stop="deleteComment(comment.commentId)"></div>
                  </div>
                  <!-- 대댓글 작성 인풋 -->
                  <div v-if="selectedComment === comment" class="mt-4 reply-container">
                    <input type="text" class="form-control reply-input" placeholder="대댓글 작성"
                      aria-label="Recipient's username" aria-describedby="reply-button" v-model="addReply" @click.stop>
                    <button class="btn btn-outline-secondary reply-btn" type="button" id="reply-button"
                      @click.stop="uploadReply()">작성</button>
                  </div>
                  <!-- 대댓글 리스트 -->
                  <div v-for="(reply, rIndex) in comment.reply" :key="`reply-${rIndex}`" class="mt-2" id="reply">
                    <p>ㄴ <b id="reply-member">{{ reply.memberId }}</b> {{ reply.content }}</p>
                    <!-- 삭제 버튼 -->
                    <div class="remove"><img src="@/assets/icons/Remove.png" alt=""
                      @click.stop="deleteReply(reply.commentId)"></div>
                  </div>
                </button>
              </div>
            </div>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>



<script setup>

import { onMounted, ref, watch } from 'vue';
import axios from 'axios';
import ReportModal from '@/components/modals/ReportModal.vue';

const props = defineProps({
  currentId: Number
});

const BASE_URL = 'https://j10a602.p.ssafy.io/api';

// 몽타쥬 좋아요
const isLiked = ref(false);
const toggleLike = () => {
  isLiked.value = !isLiked.value;
  if (isLiked.value) {
    montageInfo.value.likeCount += 1;
  } else {
    if (montageInfo.value.likeCount > 0) {
      montageInfo.value.likeCount -= 1;
    }
  }
};

// 댓글 저장
const comments = ref([]);
const montageInfo = ref([]);

// 몽타쥬정보 가져오기
const getMontageInfo = () => {
  axios.get(`${BASE_URL}/montage/list`)
    .then((response) => {
      const found = response.data.data.find(m => m.id === props.currentId); // 여기서 m.id가 몽타쥬의 ID와 일치하는지 확인
      if (found) {
        montageInfo.value = found;
        console.log('몽타쥬아이디:', props.currentId);
        console.log('좋아요개수:', montageInfo.value.likeCount);
      } else {
        alert('해당 컨텐츠를 찾을 수 없습니다.');
      }
    })
    .catch((error) => {
      console.error(error);
    });
};

// 댓글/대댓글 가져오는 메서드
const getCommentReply = () => {
  axios.get(`${BASE_URL}/montage/${props.currentId}/comment`)
    .then((response) => {
      console.log(response.data.data);
      comments.value = response.data.data;
    })
    .catch((error) => {
      console.error(error);
    });
};

// 페이지 로드 시 getCommentReply 함수 실행
onMounted(() => {
  getCommentReply();
  getMontageInfo();
});

// 좋아요 추가하기
const addLike = () => {
    axios
      .post(`${BASE_URL}/montage/${props.currentId}/like`)
      .then((response) => {
        console.log(response.data.data);
        isLiked.value = response.data.data;
      })
      .catch((error) => {
        console.error(error);
      });
};

// currentId 변화 감지시 getCommentReply 함수 실행
watch(() => props.currentId, (newId, oldId) => {
  getCommentReply();
  getMontageInfo();
  addLike();
}, { immediate: true }); 

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
      .post(`${BASE_URL}/montage/${props.currentId}/comment`, content)
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
    alert("댓글을 입력해주세요.");
  }

};

const deleteComment = (id) => {
  axios
    .delete(`${BASE_URL}/montage/${props.currentId}/comment/${id}`)
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
  if (addReply.value.trim().length > 0) {
    const content = {
      "montageId": 1,
      "parentId": selectedComment.value.commentId,
      "content": addReply.value,
      "isDeleted": false
    };
    axios
      .post(`${BASE_URL}/montage/${selectedComment.value.commentId}/comment`, content)
      .then((response) => {
        console.log(response.data);
        addReply.value = "";
        getCommentReply();
      })
      .catch((error) => {
        console.error(error);
      });
  }
  else {
    alert("대댓글을 입력해주세요.");
  }
};

// 대댓글 삭제
const deleteReply = (id) => {
  axios
    .delete(`${BASE_URL}/montage/${props.currentId}/comment/${id}`)
    .then(() => {
      console.log("대댓글이 삭제되었습니다:");
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

.accordion-body {
  max-height: 38vh;
  overflow-y: auto;
}

.accordion {
  display: flex;
}

.accordion-header {
  display: flex;
  flex-direction: row;
}



.columnthings {
  display: flex;
  flex-direction: column;
}

.rowthings {
  display: flex;
  flex-direction: row;
  height: auto;
}


.like-btn {
  background-color: white;
  border: none;
}

.like-btn img {
  width: 20px;
  height: 20px;
}

.report-btn {
  background-color: white;
  border: none;
}

.report-btn img {
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
  display: flex;
  flex-direction: row;
  align-items: center;

}
#reply img{
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

#comment-count {
  min-width: 150px;
}

.slide-enter-active,
.slide-leave-active {
  transition: all 0.3s ease;
}

.slide-enter,
.slide-leave-to

/* .slide-leave-active in <2.1.8 */
  {
  height: 0;
  opacity: 0;
}
</style>