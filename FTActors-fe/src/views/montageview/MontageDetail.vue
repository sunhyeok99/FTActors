<template>
  <div class="montagedetail">
    <div class="montage" ref="thumbnail">
      <img src="@/assets/icons/Next.png" alt="" id="previous" @click="goToPreviousMontage">
      <video :src="montage.link" muted autoplay playsinline></video>
      <img src="@/assets/icons/Next.png" alt="" id="next" @click="goToNextMontage">
    </div>
    <div class="commentreply">
      <CommentReply :current-id="currentId" />
    </div>
  </div>
</template>
<script setup>
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import { watch, onMounted, ref } from 'vue';
import CommentReply from '@/components/common/CommentReply.vue'

const SERVER_URL = 'https://j10a602.p.ssafy.io/api';
const router = useRouter();
const route = useRoute();
const thumbnail = ref(null);
const currentId = ref(parseInt(route.params.id, 10));
const montageLength = ref();

const getMontage = () => {
  axios.get(`${SERVER_URL}/montage/list`)
    .then((response) => {
      if (currentId.value >= 0 && currentId.value < response.data.data.length) {
        montage.value = response.data.data.find((m, index) => index === currentId.value);
        montageLength.value = response.data.data.length
      } else {
        console.error('Invalid montage ID:', currentId.value);
      }
    })
    .catch((error) => {
      console.error(error);
    });
};

const goToPreviousMontage = () => {
  if (currentId.value > 0) {
    const previousId = currentId.value - 1;
    router.push({ name: 'montageDetail', params: { id: previousId } });
    getMontage();
  } else {
    alert('이전 컨텐츠가 없습니다.');
  };
};
const goToNextMontage = () => {
  if (currentId.value < montageLength.value -1) {
    const nextId = currentId.value + 1;
  router.push({ name: 'montageDetail', params: { id: nextId } });
  getMontage();
  } else {
    alert('다음 컨텐츠가 없습니다.');
  };

};

const montage = ref([]);
watch(() => route.params.id, (newId) => {
  currentId.value = parseInt(newId, 10);
  getMontage();
}, { immediate: true });



onMounted(() => {
  getMontage();

});


</script>
<style scoped>
.montagedetail {
  display: flex;
  width: 100%;
}

.montage {
  align-items: center;
  display: flex;
  justify-content: center;
}

.montage video {
  height: auto;
  width: 50vw;
  z-index: 2;
  flex: 1;
  max-height: 75vh;
}

.montage img {
  width: 25px;
  height: 25px;
  margin: 2rem;
}

.commentreply {
  flex: 2;
}

#previous {
  transform: rotate(180deg);
  color-interpolation-filters: white;
  cursor: pointer;
}

#next {
  cursor: pointer;
}
.form-control {
  width: 100%; /* 너비 최대로 */
  min-height: 40px; /* 댓글 인풋의 최소 높이 */
  margin-bottom: 1rem; /* 여백 추가 */
}

/* 댓글 및 대댓글 텍스트 영역 */
textarea.form-control {
  min-height: 100px; /* 텍스트 영역 높이 */
}
/* 댓글 및 대댓글의 글씨 크기 */
.comment-text, #reply-member, .comment-count {
  font-size: 16px; /* 적절한 글씨 크기로 조정 */
}

/* 댓글 쓰기 인풋 텍스트 크기 */
.input-group .form-control {
  font-size: 14px; /* 입력란의 텍스트 크기 */
}
.commentreplycomponent {
  display: flex;
  flex-direction: column;
  width: 100%; /* 컴포넌트의 너비 최대로 조정 */
}

.accordion-header, .accordion-body {
  padding: 1rem; /* 패딩 추가로 내용과 경계 간 여백 조정 */
}

/* 작성 버튼 스타일 조정 */
.btn-outline-secondary {
  width: auto;
  padding: 0.375rem 0.75rem;
}

/* 모달 타이틀 스타일 조정 */
.modal-title {
  font-size: 20px; /* 모달 타이틀 글씨 크기 */
  margin-bottom: 1rem;
}
</style>