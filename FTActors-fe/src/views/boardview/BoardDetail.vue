<template>
  <div class="boardheader">
    <h1> <b>BOARD</b></h1>
  </div>
  <div class="boardpage">
    <img :src="recruitment.image" alt="">
    <div class="boardlist">
      <ul class="list-group list-group-flush">
        <li class="list-group-item"><label><b>공고명</b></label>{{ recruitment.title }}
          <li class="list-group-item"><label><b>공고분류</b></label>{{ recruitment.category }}</li>
        </li>
        <li class="list-group-item"><label><b>담당자</b></label>{{ recruitment.postMember }}</li>
        <li class="list-group-item"><label><b>공고분류</b></label>{{ recruitment.category }}</li>
        <li class="list-group-item"><label><b>지원시작일자</b></label>{{ recruitment.startDate }}</li>
        <li class="list-group-item"><label><b>지원마감일자</b></label>{{ recruitment.endDate }}</li>
        <!-- <RouterLink
              :to="{ name: 'boardUpdate', params: { id: recruitment.id } }"
              class="btn btn-primary"
              >공고 변경</RouterLink> -->

        <button @click="boardUpdate" class="btn-create">공고 변경</button>
        <button @click="confirmDelete" class="btn-create">공고 삭제</button>

      </ul>
    </div>
  </div>
  <div class="detailboardpage">
    <h4><b>상세 내용</b></h4>
    <p>많은 관심부탁드립니다</p>
    <h5><b>이미지</b></h5>
    <p>많은 관심부탁드립니다</p>
    <h5><b>첨부파일</b></h5>
    <p>많은 관심부탁드립니다</p>
    <h4><b>모집 배역</b></h4>
    <p>많은 관심부탁드립니다</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { recruitmentApi } from '@/util/axios';

const router = useRouter();
const recruitment = ref({});

const fetchRecruitmentDetail = async () => {
  const recruitmentId = router.currentRoute.value.params.id; // 현재 라우트의 파라미터 사용
    const response = await recruitmentApi.getDetail(recruitmentId, 1);
    recruitment.value = response.data.data
};

onMounted(fetchRecruitmentDetail);

const boardUpdate = () => {
  const boardId = recruitment.value.id;
  console.log(boardId)
  router.push({ name: 'boardUpdate' , state: { id: boardId }});
};

const confirmDelete = () => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    deleteRecruitment();
  }
};

const deleteRecruitment = async () => {
  const recruitmentId = recruitment.value.id;
  try {
    const response = await recruitmentApi.remove(recruitmentId);
    if (response.status === 200) {
      alert("삭제되었습니다.");
    } else {
      alert("삭제 실패했습니다.");
    }
    router.push({ name: 'board' });
  } catch (error) {
    console.error("Error deleting recruitment:", error);
  }
};


</script>


<style>
.boardheader {
  display: flex;
  align-items: center;
}
h1{
  font-size: 4rem;
}
.boardpage {
  display: flex;
}

.boardlist {
  width: 30rem;
}
</style>