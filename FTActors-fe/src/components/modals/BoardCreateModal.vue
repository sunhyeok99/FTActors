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
                            <select id="category" v-model="category" class="input-field">
                                <option value="">카테고리를 선택하세요</option>
                                <option value="장편영화">장편영화</option>
                                <option value="단편영화">단편영화</option>
                                <option value="뮤비/CF">웹드라마</option>
                                <option value="뮤비/CF">뮤비/CF</option>
                                <option value="뮤비/CF">유튜브/기타</option>
                              </select>
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
                    <h1 class="modal-title fs-5" id="exampleModalToggleLabel5">5단계 <b>공고 날짜</b></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="form-floating">
                        <input
                          type="date"
                          id="startDate"
                          v-model="startDate"
                          class="input-field"
                        />
                        <input
                          type="date"
                          id="endDate"
                          v-model="endDate"
                          class="input-field"
                        />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-target="#directorModalToggle6"
                    data-bs-toggle="modal">6단계</button>
                </div>
            </div>
        </div>
    </div>
     <!-- 6단계 -->
     <div class="modal fade" id="directorModalToggle6" aria-hidden="true" aria-labelledby="exampleModalToggleLabel6"
     tabindex="-1">
     <div class="modal-dialog modal-dialog-centered">
         <div class="modal-content">
             <div class="modal-header">
                 <h1 class="modal-title fs-5" id="exampleModalToggleLabel6">6단계 <b>요구사항 업로드</b></h1>
                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
             </div>
             <div class="modal-body">
                 <div class="form-floating">
                     <div class="input-group">
                        <input type="file" id="script"  @change="onScriptChange"  class="input-field"/>
                        <div v-if="selectedFile">
                        <!-- <span>{{ selectedFile.name }}</span> -->
                        <span @click="clearSelectedFile"> X</span>
                      </div>
                     </div>
                 </div>
             </div>
             <div class="modal-footer">
                 <button class="btn btn-primary" data-bs-dismiss="modal" @click="register">공고 작성</button>
             </div>
         </div>
     </div>
 </div>
    <!-- 토글 버튼 -->
    <button class="btn btn-dark" data-bs-target="#directorModalToggle" data-bs-toggle="modal" @click="register">공고 작성</button>
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