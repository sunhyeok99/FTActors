<template>
  <div class="boardheader">
    <h1> <b>BOARD</b></h1>
  </div>
  <div class="boardpage">
    <img :src="recruitment.image" alt="">
    <div class="boardlist">
      <ul class="list-group list-group-flush">
        <li class="list-group-item"><label><b>공고명</b></label>{{ recruitment.title }} </li>
        <li class="list-group-item"><label><b>담당자</b></label>{{ recruitment.postMember }}</li>
        <li class="list-group-item"><label><b>공고분류</b></label>{{ recruitment.category }}</li>
        <li class="list-group-item"><label><b>지원시작일자</b></label>{{ recruitment.startDate }}</li>
        <li class="list-group-item"><label><b>지원마감일자</b></label>{{ recruitment.endDate }}</li>
        
        <div>
          <div class="detailboardpage">
            <h4><b>공고 내용</b></h4>
            <p>{{ recruitment.content }}</p>
            <h5><b>첨부파일</b></h5>
            <p>{{ recruitment.file }}</p>
            <p>
               <a :href="recruitment.file" download="recruitment_file">파일 다운로드</a>
              </p>
          </div>
          <h3> 영상을 올릴 때 꼭 [이름]배역이름으로 파일 명을 지정해주세요.
         <br><br>  ex) [배역이름]실제이름</h3>
    <!-- recruitment.postMemberId가 로그인유저인 경우 -->
    <template v-if="checkPermission()">
      <button @click="boardUpdate" class="btn-create">공고 변경</button>
      <button @click="confirmDelete" class="btn-create">공고 삭제</button>
    </template>
    <!-- recruitment.postMemberId가 로그인 유저가 아닌 경우 -->
    <template v-else>
      <button v-if="recruitment.apply === 1" class="btn-applied">이미 지원하였습니다</button>
      <button v-else @click="apply" class="btn-create">지원하기</button>
    </template>
  </div>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { recruitmentApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";
  
const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
const adminId = 1;
const router = useRouter();
const recruitment = ref({});

const fetchRecruitmentDetail = async () => {
  const recruitmentId = router.currentRoute.value.params.id; // 현재 라우트의 파라미터 사용
  if (loginMember.value == "" || loginMember.value == null) {
    const response = await recruitmentApi.getDetail(recruitmentId, adminId);
    recruitment.value = response.data.data;
  } else {
    const memberId = loginMember.value;
    const response = await recruitmentApi.getDetail(recruitmentId, memberId);
    recruitment.value = response.data.data;
  }
};

onMounted(fetchRecruitmentDetail);

const checkPermission = () => {
  return loginMember.value == recruitment.value.postMemberId;
};

const boardUpdate = () => {
  const boardId = recruitment.value.id;
  router.push({ name: 'boardUpdate', state: { id: boardId }});
};

const confirmDelete = () => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    deleteRecruitment();
  }
};

const apply = () => {
  if (loginMember.value == "" || loginMember.value == null) {
    alert("로그인이 필요합니다")
    router.push({ name: 'login'});
  } else {
    const recruitmentId = recruitment.value.id;
    router.push({ name: 'applyCreate', params: { recruitmentId: recruitmentId, memberId : loginMember.value }});
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
