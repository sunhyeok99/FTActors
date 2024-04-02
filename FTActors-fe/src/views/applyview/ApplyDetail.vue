<template>
    <div class="boardheader">
      <h3> <b>지원 내역</b></h3>
    </div>
    <div class="boardpage">
      <div class="boardlist">
        <ul class="list-group list-group-flush">
          <li class="list-group-item"><label><b>공고명</b></label>{{ apply.recruitmentTitle }} </li>
          <li class="list-group-item"><label><b>지원자 이름</b></label>{{ apply.memberName }}</li>
          <li class="list-group-item"><label><b>지원 내용</b></label>{{ apply.content }}</li>
          <li class="list-group-item"><label><b>지원 날짜</b></label>{{ apply.createdAt }}</li>
          <p>
            <p>{{ truncateVideoName(apply.videoName) }}</p>
            <a :href="apply.videoLink" download="apply_file">파일 다운로드</a>
          </p>
          <button @click="goToBoardDetail(apply.recruitmentId)" class="btn-create">공고 보기</button>
        <button @click="confirmDelete" class="btn-create">지원 삭제</button>
  
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

  const router = useRouter();
  const apply = ref({});

  const fetctApplyDetail = async () => {
    const applyId = router.currentRoute.value.params.id; // 현재 라우트의 파라미터 사용
      const response = await recruitmentApi.getApplyDetail(applyId);
      apply.value = response.data.data
      console.log(apply.value)
  };
  
  onMounted(fetctApplyDetail);
 
  
  const confirmDelete = () => {
    if (confirm("정말로 삭제하시겠습니까?")) {
      deleteApply();
    }
  };
  
  const deleteApply = async () => {
    console.log(apply.value.recruitmentId)
    try {
      const response = await recruitmentApi.cancelApply(apply.value.recruitmentId, loginMember.value);
      if (response.status === 200) {
        alert("삭제되었습니다.");
      } else {
        alert("삭제 실패했습니다.");
      }
      router.push('/myPage');
    } catch (error) {
      console.error("Error deleting recruitment:", error);
    }
  };

  const goToBoardDetail = (boardId) => {
  router.push({ name: 'boardDetail', params: { id: boardId } });
};

const truncateVideoName = (name) => {
  if (!name) return ''; // name이 정의되어 있지 않으면 빈 문자열 반환
  for (let i = 0; i < name.length; i++) {
    if (name.substring(i, i + 1) === "_") {
      return name.substring(i + 1, name.length);
    }
  }
  return name;
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