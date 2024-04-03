<template>
  <div class="about">
    <h1>공고 제작 페이지</h1>
    <fieldset>
      <label for="title">공고제목</label>
      
      <label for="content">내용</label>
      <textarea id="content" v-model="content" placeholder="공고 내용을 입력해주세요" class="input-field"></textarea>
      <label for="postMemberId">게시 회원 ID</label>
      <input type="text" id="postMemberId" v-model="postMemberId" placeholder="게시 회원 ID를 입력해주세요" class="input-field" />
      <label for="category">카테고리</label>
      <input type="text" id="category" v-model="category" placeholder="카테고리를 입력해주세요" class="input-field" />
      <label for="image">이미지</label>
      <input type="file" id="image" @change="onFileChange" class="input-field" />
      <img :src="selectedImage">
      <label for="startDate">시작 날짜</label>
      <input type="date" id="startDate" v-model="startDate" class="input-field" />
      <label for="endDate">종료 날짜</label>
      <input type="date" id="endDate" v-model="endDate" class="input-field" />
      <button class="btn-register" @click="register">
        공고 등록
      </button>
    </fieldset>

      <!-- 1단계 -->
  <div class="modal fade" id="boardModalToggle" aria-hidden="true" aria-labelledby="boardModalToggleLabel"
  tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
          <div class="modal-header">
              <h1 class="modal-title fs-5" id="boardModalToggleLabel">1단계 공고제목</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <div class="input-group mb-3">
                  <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                      data-bs-toggle="dropdown" aria-expanded="false">Dropdown</button>
                  <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="#">Action</a></li>
                      <li><a class="dropdown-item" href="#">Another action</a></li>
                      <li><a class="dropdown-item" href="#">Something else here</a></li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>
                      <li><a class="dropdown-item" href="#">Separated link</a></li>
                  </ul>
            
                  <input type="text" class="form-control" aria-label="Text input with dropdown button">
              </div>
          </div>
          <div class="modal-footer">
              <button class="btn btn-secondary" data-bs-target="#boardModalToggle2" data-bs-toggle="modal">
                  2단계</button>
          </div>
      </div>
  </div>
</div>

<!-- 2단계 -->
<div class="modal fade" id="boardModalToggle2" aria-hidden="true" aria-labelledby="boardModalToggleLabel2"
  tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
          <div class="modal-header">
              <h1 class="modal-title fs-5" id="boardModalToggleLabel2">2단계 자기소개</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <div class="form-floating">
                  <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2"
                      style="height: 100px"></textarea>
                  <label for="floatingTextarea2">자기소개를 입력해주세요</label>
              </div>
          </div>
          <div class="modal-footer">
              <button class="btn btn-secondary" data-bs-target="#boardModalToggle3"
                  data-bs-toggle="modal">3단계</button>
          </div>
      </div>
  </div>
</div>
<!-- 3단계 -->
<div class="modal fade" id="boardModalToggle3" aria-hidden="true" aria-labelledby="boardModalToggleLabel3"
  tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
          <div class="modal-header">
              <h1 class="modal-title fs-5" id="boardModalToggleLabel3">3단계 포트폴리오 업로드</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <div class="form-floating">
                  <div class="input-group">
                      <input type="file" class="form-control" id="inputGroupFile04"
                          aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                      <button class="btn btn-outline-secondary" type="button"
                          id="inputGroupFileAddon04">업로드</button>
                  </div>
              </div>
          </div>
          <div class="modal-footer">
              <button class="btn btn-secondary" data-bs-target="#boardModalToggle4"
                  data-bs-toggle="modal">4단계</button>
          </div>
      </div>
  </div>
</div>
<!-- 4단계 -->
<div class="modal fade" id="boardModalToggle4" aria-hidden="true" aria-labelledby="boardModalToggleLabel4"
  tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
          <div class="modal-header">
              <h1 class="modal-title fs-5" id="boardModalToggleLabel4">4단계 추가 사진 업로드</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <div class="form-floating">
                  <div class="input-group">
                      <input type="file" class="form-control" id="inputGroupFile04"
                          aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                      <button class="btn btn-outline-secondary" type="button"
                          id="inputGroupFileAddon04">업로드</button>
                  </div>
              </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">프로필 생성</button>
          </div>
      </div>
  </div>
</div>


<!-- 토글 버튼 -->
<button class="btn btn-primary" data-bs-target="#boardModalToggle" data-bs-toggle="modal">감독 프로필 만들기</button>
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
  if (file != null) {
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