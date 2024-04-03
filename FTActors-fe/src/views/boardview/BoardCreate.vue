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
      <label for="postMemberId">회원 이름</label>
        <p>{{ loginMember.name }}</p>
      <label for="category">카테고리</label>
      <select id="category" v-model="category" class="input-field">
        <option value="">카테고리를 선택하세요</option>
        <option value="장편영화">장편영화</option>
        <option value="단편영화">단편영화</option>
        <option value="뮤비/CF">웹드라마</option>
        <option value="뮤비/CF">뮤비/CF</option>
        <option value="뮤비/CF">유튜브/기타</option>
      </select>
      <label for="image">이미지</label>
      <input type="file" id="image"  @change="onImageChange"  class="input-field" />
      <div v-if="selectedImage">
      <span @click="clearSelectedImage"> X</span></div>
      <img :src="selectedImage" v-if="selectedImage">
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
      <label for="script">첨부파일</label>
      <input type="file" id="script"  @change="onScriptChange"  class="input-field"/>
      <div v-if="selectedFile">
      <!-- <span>{{ selectedFile.name }}</span> -->
      <span @click="clearSelectedFile"> X</span>
    </div>
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
import { useMemberStore } from "@/stores/member-store.js";
  
  const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;

const router = useRouter();
const title = ref("");
const content = ref("");
const postMemberId = ref("");
let image = null;
const category = ref("");
const startDate = ref("");
const endDate = ref("");
let script = null;

const selectedImage = ref(null);
const selectedFile = ref(null);

let fileReader = new FileReader(); // FileReader 변수를 함수 외부에서 정의
let imageReader = new FileReader();

const onImageChange = (e) => {
  const file = e.target.files[0];
  if(file != null){
    imageReader.onload = () => {
      selectedImage.value = imageReader.result;
    };
    imageReader.readAsDataURL(file);
    image = file;
    console.log(imageReader)
  }
  else{
    image = null;
    imageReader.onload = null; // reader 초기화
    imageReader = new FileReader(); // 새로운 FileReader 객체 생성
    selectedImage.value = null; // 이미지 데이터 초기화
  }
};
const clearSelectedImage = () => {
      selectedImage.value = null
      const input = document.getElementById('image');
  input.value = ''; // input 요소의 값을 초기화하여 파일 이름을 지움
    image = null;
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



const register = async () => {
let formData = new FormData();
formData.append("title", title.value);
formData.append("content", content.value);
formData.append("postMemberId", loginMember.value);
formData.append("category", category.value);
formData.append("image", image);
formData.append("startDate", startDate.value);
formData.append("endDate", endDate.value);
formData.append("memberId", loginMember.value);
  try {
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
      flex-direction: column;
    }
  }
  </style>