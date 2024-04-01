<template>
    <!-- 1단계 -->
    <div class="modal fade" id="directorModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel"
        tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalToggleLabel">1단계 <b>공고 제목</b></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label"  id="title" >제목</label>
                        <input type="email" class="form-control" id="exampleFormControlInput1" v-model="title"
                            placeholder="ex) [배우모집] 고등학생 역할 30세 이하 남배우 구합니다">
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
                    <h1 class="modal-title fs-5" id="exampleModalToggleLabel2">2단계 <b>공고내용</b></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2"
                            style="height: 100px" v-model="content"></textarea>
                        <label for="floatingTextarea2">공고 내용을 입력해주세요 ex)촬영 위치: 부산, 인원 : 00명</label>
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
                    <h1 class="modal-title fs-5" id="exampleModalToggleLabel3">3단계 <b>카테고리 선택</b></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating">
                        <div class="mb-3">
                            <label for="exampleFormControlInput1" class="form-label"  id="title" >카테고리 입력</label>
                            <input type="email" class="form-control" id="exampleFormControlInput1" v-model="title"
                                placeholder="ex) 연극, 웹드라마, 방송...">
                        </div>
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
                                aria-describedby="inputGroupFileAddon04" aria-label="Upload" @change="onFileChange">
                                <img :src="selectedImage">
                            <button class="btn btn-outline-secondary" type="button"
                                id="inputGroupFileAddon04">업로드</button>
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
                    <h1 class="modal-title fs-5" id="exampleModalToggleLabel5">5단계 <b>마감 날짜</b></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating">
                        <label for="endDate">종료 날짜</label>
                        <input type="date" id="endDate" v-model="endDate" class="input-field" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal">공고 생성</button>
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
      flex-direction: column;
    }
  }
</style>