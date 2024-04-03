<template>
  <!-- 1단계 -->
  <div class="modal fade" id="directorModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalToggleLabel">1단계 <b>배우 or 감독</b></h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <div class="mb-3">
                    <input type="radio" id="actor" value="배우" v-model="category">
                    <label for="actor">배우</label>
                    
                    <input type="radio" id="director" value="감독" v-model="category">
                    <label for="director">감독</label>
                  </div>
              </div>
              <div class="modal-footer">
                  <button class="btn btn-secondary" data-bs-target="#directorModalToggle2" data-bs-toggle="modal">
                      2단계</button>
              </div>
          </div>
      </div>
  </div>

  <!-- 2단계 -->
  <div class="modal fade" id="directorModalToggle2" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalToggleLabel2">2단계 <b>자기소개를 작성해주세요</b></h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <div class="form-floating">
                      <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2"
                          style="height: 100px" v-model="content"></textarea>
                      <label for="floatingTextarea2">자기소개를 간략하게 작성해주세요</label>
                  </div>
              </div>
              <div class="modal-footer">
                  <button class="btn btn-secondary" data-bs-target="#directorModalToggle3"
                      data-bs-toggle="modal">3단계</button>
              </div>
          </div>
      </div>
  </div>
  <!-- 3단계 -->
  <div class="modal fade" id="directorModalToggle3" aria-hidden="true" aria-labelledby="exampleModalToggleLabel3"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="portfolioLink">3단계 <b>포트폴리오 등록</b></h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <div class="form-floating">
                    <textarea class="form-control" placeholder="Leave a link here" id="floatingTextarea2"
                          style="height: 100px" v-model="portfolioLink"></textarea>
                      <label for="floatingTextarea2">포트폴리오 링크를 작성해주세요</label>
                  </div>
              </div>
              <div class="modal-footer">
                  <button class="btn btn-secondary" data-bs-target="#directorModalToggle4"
                      data-bs-toggle="modal">4단계</button>
              </div>
          </div>
      </div>
  </div>
  <!-- 4단계 -->
  <div class="modal fade" id="directorModalToggle4" aria-hidden="true" aria-labelledby="exampleModalToggleLabel4"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalToggleLabel4">4단계 <b>이미지 업로드</b></h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <div class="form-floating">
                      <div class="input-group">
                          <input type="file" class="form-control" id="inputGroupFile04"
                              aria-describedby="inputGroupFileAddon04" aria-label="Upload"  @change="onImageChange">
                              <div v-if="selectedImage">
                                  <span @click="clearSelectedImage"> X</span></div>
                                  <img :src="selectedImage" v-if="selectedImage">
                      </div>
                  </div>
              </div>
              <div class="modal-footer">
                  <button class="btn btn-secondary" data-bs-target="#directorModalToggle5"
                      data-bs-toggle="modal">5단계</button>
              </div>
          </div>
      </div>
  </div>
  <!-- 5단계 -->
  <div class="modal fade" id="directorModalToggle5" aria-hidden="true" aria-labelledby="exampleModalToggleLabel5"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalToggleLabel5">5단계 <b>비밀계정으로 하시겠습니까</b></h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                  <div class="form-floating">
                         <input type="radio" id="yes" value="예" v-model="privated">
                          <label for="yes">예</label>
                          
                          <input type="radio" id="no" value="아니요" v-model="privated">
                          <label for="no">아니요</label>
                  </div>
              </div>
              <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-target="#directorModalToggle6"
                  data-bs-toggle="modal">프로필 생성</button>
              </div>
          </div>
      </div>
  </div>
   <!-- 6단계 -->
   <div class="modal fade" id="directorModalToggle6" aria-hidden="true" aria-labelledby="exampleModalToggleLabel6"
   tabindex="-1">
   <div class="modal-dialog modal-dialog-centered">
       <div class="modal-content">
           <div class="modal-footer">
               <button class="btn btn-primary" data-bs-dismiss="modal" @click="register">프로필 생성</button>
           </div>
       </div>
   </div>
</div>



  <!-- 토글 버튼 -->
  <button class="btn btn-primary" data-bs-target="#directorModalToggle" data-bs-toggle="modal" @click="register">공고 작성</button>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { profileApi, recruitmentApi } from "../../util/axios.js";
import { useMemberStore } from "@/stores/member-store.js";


const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;

const router = useRouter();
const category = ref("");
const content = ref("");
const portfolioLink = ref("");
let image = null;
const privated= ref("");

const selectedImage = ref(null);
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

const register = async () => {
let formData = new FormData();
formData.append("category", category.value);
formData.append("content", content.value);
formData.append("portfolioLink", portfolioLink.value);
formData.append("image", image);
formData.append("privated", privated.value);
try {
  const response = await profileApi.post(formData);
  if (response.status === 200) {
  // 등록 성공 시 알림창 표시
  alert("등록 성공");    
} else {
  // 등록 실패 시 처리
  alert("등록 실패");    
}  
router.push({ name: 'profile' });

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