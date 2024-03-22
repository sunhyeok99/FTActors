<template>
  <div class="about">
    <h1>공고 제작 페이지</h1>
    <fieldset>
      <label for="title">공고제목</label>
      <input type="text" id="title"  v-model="title" placeholder = "공고 제목을 입력해주세요"
        class="input-field"
      />
      <label for="content">내용</label>
      <textarea
        id="content"
        v-model="content"
        placeholder="공고 내용을 입력해주세요"
        class="input-field"
      ></textarea>
      <label for="postMemberId">게시 회원 ID</label>
      <input
        type="text"
        id="postMemberId"
        v-model="postMemberId"
        placeholder="게시 회원 ID를 입력해주세요"
        class="input-field"
      />
      <label for="category">카테고리</label>
      <input
        type="text"
        id="category"
        v-model="category"
        placeholder="카테고리를 입력해주세요"
        class="input-field"
      />
      <label for="image">이미지</label>
      <input type="file" id="image"  @change="onFileChange"  class="input-field"/>
      <img :src="selectedImage">
      <label for="startDate">시작 날짜</label>
      <input
        type="date"
        id="startDate"
        v-model="startDate"
        class="input-field"
      />
      <label for="endDate">종료 날짜</label>
      <input
        type="date"
        id="endDate"
        v-model="endDate"
        class="input-field"
      />
      <button class="btn-register" @click="register">
        공고 등록
      </button>
    </fieldset>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { recruitmentApi } from "../../util/axios.js";
const router = useRouter();

const title = ref("");
const content = ref("");
const postMemberId = ref("");
let image = null;
const category = ref("");
const startDate = ref("");
const endDate = ref("");
const memberId = ref("");

const selectedImage = ref(null);


const onFileChange = (e) => {
  const file = e.target.files[0];
  const reader = new FileReader();
  if(file != null){
    reader.onload = () => {
      selectedImage.value = reader.result;
    };
    reader.readAsDataURL(file);
    image = file;
  }
};

const register = async () => {
let formData = new FormData();
formData.append("title", title.value);
formData.append("content", content.value);
formData.append("postMemberId", postMemberId.value);
formData.append("category", category.value);
formData.append("image", image);
formData.append("startDate", startDate.value);
formData.append("endDate", endDate.value);
formData.append("memberId", postMemberId.value);
  try {
    console.log(formData)
    const response = await recruitmentApi.register(formData);
    if (response.status === 200) {
    // 등록 성공 시 알림창 표시
    alert("등록 성공");    
  } else {
    // 등록 실패 시 처리
    alert("등록 실패");    
  }  
  router.push({ name: 'board' });

} catch (error) {
    console.error("Error registering recruitment:", error);
    // 오류 처리
  }
};


</script>


  <style>
  @media (min-width: 1024px) {
    .about {
      min-height: 100vh;
      display: flex;
      align-items: center;
    }
  }
  </style>