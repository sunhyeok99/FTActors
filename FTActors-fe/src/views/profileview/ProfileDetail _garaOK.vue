<template>
<div class="profileheader">
  <h1> <b>PROFILE</b></h1>
  </div>
  <div class="profilepage">
    <img src="@/assets/actors/소희.jpg" alt="">
    <div class="profilelist">
      <ul class="list-group list-group-flush">
        <li class="list-group-item"><label><b>이름</b></label> 
          <div class="profiletab">
          <button type="button" class="btn btn-dark" id="updatebtn" @click="goToProfileUpdate">수정하기</button>
          <!-- 신고 버튼 -->
          <!-- <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#reportModal">
            <img src="@/assets/icons/Siren.png" alt="">
          </button> -->
          <!-- <ReportModal /> -->
        </div>
        {{ profile.name }} </li>
        <li class="list-group-item"><label><b>닉네임</b></label> {{ profile.stageName }} </li>
        <li class="list-group-item"><label><b>이메일</b></label> {{ profile.email }} </li>
        <li class="list-group-item"><label><b>전화번호</b></label> {{ profile.phoneNumber }} </li>
        <li class="list-group-item"><label><b>이름</b></label> 배사람 </li>
        <li class="list-group-item"><label><b>닉네임</b></label>춤추는 산양 </li>
        <li class="list-group-item"><label><b>이메일</b></label> bausa@gmail.com </li>
        <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
        <li class="list-group-item"><label><b>이름</b></label> 배사람 </li>
        <li class="list-group-item"><label><b>닉네임</b></label>춤추는 산양 </li>
        <li class="list-group-item"><label><b>이메일</b></label> bausa@gmail.com </li>
        <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
      </ul>
    </div>
  </div>
  <div class="detailprofilepage">
    <h4><b>자기소개</b></h4>
    <p>안녕하세요 배우 안기영입니다 :)
      항상 맡은 바 책임을 다하는 배우가 되겠습니다.

      이름 : 안기영
      나이 : 27세 (1996.12.08)
      신장 : 180cm / 65kg
      학력 : 서울예대 연기전공 졸업
      특기 : 액션, 춤, 악기연주(기타/플룻), 노래
      연락처 : 01076777064 / gi0miso@naver.com /
      https://www.instagram.com/hug.0a/

      연기영상 : https://youtu.be/R0tEXCYsBt4
      https://youtu.be/R0tEXCYsBt4</p>

    <h4><b>학력</b></h4>
    <p>서울예술대학교 연기전공</p>
    <h4><b>경력</b></h4>
    <p></p>
    <h4><b>추가 이미지</b></h4>
    <p></p>
    <h4><b>연기영상</b></h4>
    <p></p>
  </div>
감독 프로필

</template>
<script setup>

import { ref, reactive, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router'
// import ReportModal from '@/components/modals/ReportModal.vue';

const router = useRouter();
const profileId = 20; // 프로필 ID 가져오기

// 프로필 수정 페이지로 이동하는 함수
const goToProfileUpdate = () => {
  router.push({ name: 'profileUpdate' });
};

// 프로필 정보를 담을 객체 생성
const profile = ref({
  // name: '',
  stageName: '',
  email: '',
  phoneNumber: '',
  content: '',
  // education: '',
  // career: '',
  imageUrl: '', // 프로필 이미지 URL
  additionalImages: [], // 추가 이미지 목록
  // videos: [] // 연기영상 목록
});

// 프로필 상세 정보를 가져오는 함수 정의
const fetchProfileDetail = async () => {
  try {
    // const profileId = 20; // 프로필 ID 또는 해당하는 정보의 ID
    const response = await axios.get(`/profile/detail?profile_id=${profileId}`);
    const data = response.data; // API 응답에서 데이터 추출
    // API 응답에서 가져온 프로필 정보를 프로필 객체에 설정
    profile.value = {
      name: data.name,
      stageName: data.stageName,
      email: data.email,
      phoneNumber: data.phoneNumber,
      content: data.content,
      // education: data.education,
      // career: data.career,
      imageUrl: data.imageUrl,
      additionalImages: data.additionalImages,
      // videos: data.videos
    };
  } catch (error) {
    console.error('Error fetching profile detail:', error);
  }
};

// 컴포넌트가 마운트된 후 프로필 정보를 가져오는 함수 호출
onMounted(() => {
  fetchProfileDetail();
});

// // 프로필 정보를 가져오는 함수
// const fetchProfileInfo = async () => {
//   try {
//     const response = await axios.get('/profile'); // 백엔드 API 엔드포인트 설정 필요
//     profileInfo.value = response.data;
//   } catch (error) {
//     console.error('Error fetching profile info:', error);
//   }
// };


// const profile = reactive({
//   name: '박소영',
//   stageName: '싸피다니엘',
//   email: 'daniel@gmail.com',
//   phoneNumber: '010-1234-1234'
// });


</script>
<style scoped>
h1 {
  font-size: 4rem;
}

.profileheader {
  display: flex;
  align-items: center;
  font-size: 4rem;
}

 .profiletab {
  height: 1rem;
  margin-left: auto;
} 

.profiletab p {
  margin-bottom: 0;
}
.profiletab img {
  width: 24px;
  height: 24px;

}

.profilepage {
  display: flex;
}

.profilelist {
  width: 30rem;
}

.detailprofilepage {
  width: 50%;
}
</style>