<template>
  <!-- <div class="container h-100">
    <div class="row justify-content-center align-items-start" style="min-height: 100vh;">
      <div class="col-md-6 col-lg-4">
        <div class="mt-5 pt-5">
          <div class="text-center mb-4">
            <h1><b>Apply</b></h1>
          </div>
          <div class="form-group mb-3">
            <h1>{{ recruitment.title }}</h1>
          </div>
          <div class="form-group mb-3">
            <label for="postMemberId">회원 이름</label>
            <p>{{ recruitment.loginName }}</p>
          </div>
          <div class="form-group mb-3">
            <label for="content">지원 내용</label>
            <textarea id="content" v-model="content" class="form-control" placeholder="지원 내용을 간단하게 적어주세요"></textarea>
          </div>
          <div class="form-group mb-3">
            <label for="script">첨부 파일</label>
            <input type="file" id="script" class="form-control" @change="onScriptChange">
          </div>
          <button @click="apply" class="btn btn-dark w-100 mb-2">지원하기</button>
        </div>
      </div>
    </div>
  </div>
 -->




<div class="modal fade" id="applyModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title fs-5" id="exampleModalLabel"><b>배우로 지원하기</b></h3>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" @click="clearAll"></button>
      </div>
      <div class="modal-body">
       <!--  <div class="form-group mb-3">
          <h1>{{ recruitment.title }}</h1>
        </div>
        <div class="form-group mb-3">
          <label for="postMemberId"><b>회원 이름</b></label>
          <p>{{ recruitment.loginName }}</p>
        </div> -->
        <div class="form-group mb-3">
          <label for="content"><h6><b>지원 내용</b></h6></label>
          <textarea id="content" v-model="content" class="form-control" placeholder="지원 내용을 간단하게 적어주세요"></textarea>
        </div>
        <div class="form-group mb-3">
          <label for="script"><h6><b>지원 영상</b></h6></label>
          <input type="file" id="script" class="form-control" @change="onScriptChange">
        </div>

        <div class="form-group mb-3" v-if="selectedFile">
          <div class="rowthings">
          <label for="scriptPreview"><h6><b>미리보기</b></h6></label>
          <span @click="clearSelectedFile" style="cursor:pointer;"><b>X</b></span>
        </div>
          <video id="scriptPreview" controls width="100%">
            <source :src="selectedFile" type="video/mp4">
          </video>

        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-dark" @click="apply">지원하기</button>
      </div>
    </div>
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

let script = null;
let fileReader = new FileReader(); // FileReader 변수를 함수 외부에서 정의

  const content = ref("");
  
  const router = useRouter();
  const recruitment = ref({});
  const selectedFile = ref(null);
  const showModal = ref(false);

  
  const fetchRecruitmentDetail = async () => {
    const recruitmentId = router.currentRoute.value.params.recruitmentId; // 현재 라우트의 파라미터 사용
    const response = await recruitmentApi.getDetail(recruitmentId, loginMember.value);
    recruitment.value = response.data.data
    setTimeout(() => {
    showModal.value = true;
  }, 2000);
};

  onMounted(fetchRecruitmentDetail);
  
  const apply = async () => {
  let formData = new FormData();
  formData.append("recruitmentId", recruitment.value.id);
  formData.append("memberId", loginMember.value);
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
const clearAll = ()=>{

}
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
  .rowthings{
    display: flex;
    justify-content: space-between;
  }
  </style>