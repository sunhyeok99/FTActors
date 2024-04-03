<template>
  <div class="profileheader">
    <h1> <b>PROFILE</b></h1>
    </div>
    <div class="profilepage">
      <li class="list-group-item" id="update-form">
          <label for="image">이미지</label>
          <input type="file" id="image" @change="onImageChange" class="input-field" />
          <div v-if="selectedImage">
            <span @click="clearSelectedImage"> X</span>
          </div>
        </li>>
          <div class="profilelist">
        <ul class="list-group list-group-flush">
          <li class="list-group-item"><label for="type">타입</label>
          <div>
            <input type="radio" id="actor" value="A" v-model="profile.type">
            <label for="actor">배우</label>
          </div>
          <div>
            <input type="radio" id="director" value="D" v-model="profile.type">
            <label for="director">감독</label>
          </div> </li>
          <li class="list-group-item"><label><b>비밀계정</b></label></li>
          <li class="list-group-item"><label><b>자기소개</b></label>
          <input v-model="profile.content" type="text" class="form-control">   </li>
          <li class="list-group-item"><label><b>포트폴리오 링크</b></label>
            <input v-model="profile.portfolio" type="text" class="form-control">   </li>
        </ul>
      </div>
      <button @click="updateProfile" class="btn btn-primary">수정</button>
    </div>
    <div class="detailprofilepage">
    
    </div>
  
  
  </template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { profileApi, recruitmentApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";

const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;

const router = useRouter();
const profile = ref({});

const selectedImage = ref(null);
let image = null;
let imageReader = new FileReader();

const onImageChange = (e) => {
  const file = e.target.files[0];
  if (file != null) {
    imageReader.onload = () => {
      selectedImage.value = imageReader.result;
    };
    imageReader.readAsDataURL(file);
    image = file;
    console.log(imageReader)
  }
  else {
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

const profileDetail = async () => {
  const profileId = router.currentRoute.value.params.id; // 현재 라우트의 파라미터 사용
    const response = await profileApi.getDetailProfile(profileId);
    profile.value = response.data.data;
};

onMounted(profileDetail);


const updateProfile = async () => {
  let formData = new FormData();
  formData.append("id", profile.value.id);
  formData.append("title", profile.value.title);
  formData.append("content", profile.value.content);
  formData.append("category", profile.value.category);
  formData.append("image", image);

  try {
    console.log(formData)
    const response = await profileApi.modifyProfile(profile.value.id ,formData);
    if (response.status === 200) {
      alert("변경 성공");
    } else {
      // 등록 실패 시 처리
      alert("변경 실패");
    }
    router.push({ name: 'profiledetail' , params : {id: profile.value.id} });
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

.imageFile {
  display: flex;
  flex-direction: column;
}

#update-form {
  display: flex;
  flex-direction: row;
}
</style>
