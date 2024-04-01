<template>
    <div class="boardheader">
      <h1> <b>Apply</b></h1>
    </div>
    <div class="applypage">
       <label for="title">공고 제목</label>
        <p>{{ recruitment.title }}</p>
  
        <label for="postMemberId">회원 이름</label>
        <p>{{ recruitment.postMemberName }}</p>
  
        <label for="content">지원 내용</label>
        <textarea  type="text"  id="content"  v-model="content"  placeholder="지원 내용을 간단하게 적어주세요"  class="input-field" width="400px" height="400px"></textarea>  
        <p></p>
  
        <label for="script">첨부 파일</label>
        <input type="file" id="script" class="form-control" @change="onScriptChange">
        <div v-if="selectedFile">
      <span @click="clearSelectedFile"> X</span>
    </div>

    </div>
    <button @click="apply" class="btn-create">지원하기</button>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRouter } from 'vue-router';
  import { recruitmentApi } from '@/util/axios';
  import { useMemberStore } from "@/stores/member-store.js";
  
const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;

let script = null;
let fileReader = new FileReader(); // FileReader 변수를 함수 외부에서 정의

  const content = ref("");
  
  const router = useRouter();
  const recruitment = ref({});
  const selectedFile = ref(null);

  
  const fetchRecruitmentDetail = async () => {
    const recruitmentId = router.currentRoute.value.params.recruitmentId; // 현재 라우트의 파라미터 사용
    const response = await recruitmentApi.getDetail(recruitmentId, loginMember.id);
      recruitment.value = response.data.data
};
  
  onMounted(fetchRecruitmentDetail);
  
  const apply = async () => {
  let formData = new FormData();
  formData.append("recruitmentId", recruitment.value.id);
  formData.append("memberId", loginMember.id);
  formData.append("videoFile", script);
  formData.append("content", content.value);
    try {
      console.log(formData)
      const response = await recruitmentApi.apply(formData);
      if (response.status === 200) {
      // 등록 성공 시 알림창 표시
      alert("지원 성공");    
    } else {
      // 등록 실패 시 처리
      alert("지원 실패");    
    }  
    router.push({ name: 'board' });
  
  } catch (error) {
      console.error("Error registering recruitment:", error);
      // 오류 처리
    }
};

  const clearSelectedFile = () => {
      selectedFile.value = null
      const input = document.getElementById('script');
  input.value = '';
  script = null;
};

const onScriptChange = (e) => {
  const file = e.target.files[0];
  if(file != null){
    fileReader.onload = () => {
      selectedFile.value = fileReader.result;
    };
    fileReader.readAsDataURL(file);
  script = file;
  }
  else{
    script = null;
    fileReader.onload = null; // reader 초기화
    fileReader = new FileReader(); // 새로운 FileReader 객체 생성
    selectedFile.value = null; // 이미지 데이터 초기화
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