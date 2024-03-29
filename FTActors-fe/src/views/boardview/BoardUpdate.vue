<template>
  <div class="boardheader">
    <h1><b>BOARD Update</b></h1>
  </div>
  <div class="boardpage">
    <input type="file" class="form-control" @change="onFileChange">
    <img :src="selectedImage" alt="현재 선택된 이미지">
    <div class="boardlist">
      <ul class="list-group list-group-flush">
        <li class="list-group-item">
          <label><b>공고명</b></label>
          <input v-model="editedRecruitment.title" type="text" class="form-control" >
        </li>
        <li class="list-group-item">
          <label><b>공고분류</b></label>
          <input v-model="editedRecruitment.category" type="text" class="form-control">
        </li>
        <li class="list-group-item">
          <label><b>담당자</b></label>
          {{ editedRecruitment.postMember }}
        </li>
        <li class="list-group-item">
          <label><b>지원시작일자</b></label>
          <input v-model="editedRecruitment.startDate" type="date" class="form-control">
        </li>
        <li class="list-group-item">
          <label><b>지원마감일자</b></label>
          <input v-model="editedRecruitment.endDate" type="date" class="form-control">
        </li>
      </ul>
    </div>
    <button @click="updateRecruitment" class="btn btn-primary">수정</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { recruitmentApi } from '@/util/axios';

const router = useRouter();
const boardId = ref('');
const editedRecruitment = ref({});

const selectedImage = ref(null);
let image = null;

const onFileChange = (e) => {
  const file = e.target.files[0];
  const reader = new FileReader();
  reader.onload = () => {
    selectedImage.value = reader.result;
  };
  reader.readAsDataURL(file);
  image = file;
};

const fetchRecruitmentDetail = async () => {
  try {
    const recruitmentId = history.state.id;
    const response = await recruitmentApi.getDetail(recruitmentId, 1);
    editedRecruitment.value = response.data.data;
     // 초기 이미지 설정
     if (response.data.data.image != null) {
      selectedImage.value = response.data.data.image;
    }
  } catch (error) {
    console.error('Error fetching recruitment detail:', error);
  }
};

onMounted(fetchRecruitmentDetail);

const updateRecruitment = async () => {
  let formData = new FormData();
  console.log(image)
  if(image != null){
    formData.append("image", image);
  };
      
formData.append("id", editedRecruitment.value.id);
formData.append("title", editedRecruitment.value.title);
formData.append("content", editedRecruitment.value.content);
formData.append("postMemberId", editedRecruitment.value.postMemberId);
formData.append("category", editedRecruitment.value.category);
formData.append("startDate", editedRecruitment.value.startDate);
formData.append("endDate", editedRecruitment.value.endDate);
formData.append("memberId", editedRecruitment.value.postMemberId);

  try {
    console.log(formData)
    const response = await recruitmentApi.update(formData);
    if (response.status === 200) {
    alert("변경 성공");    
  } else {
    // 등록 실패 시 처리
    alert("변경 실패");    
  }  
  router.push({ name: 'board' });
} catch (error) {
    console.error("Error registering recruitment:", error);
    // 오류 처리
  }
};

</script>

<style>
.boardheader {
  display: flex;
  align-items: center;
}
h1 {
  font-size: 4rem;
}
.boardpage {
  display: flex;
}
.boardlist {
  width: 30rem;
}
</style>
