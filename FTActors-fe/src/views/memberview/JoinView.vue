<template>
    <div class="row">
        <div class="col-md-3">
            <div>
                <h1><b>회원가입</b></h1>
            </div>
            <div>
                <form>
                    <div class="form-group id-group">
            <label for="id" class="form-label">아이디</label>
            <input
              type="text"
              id="id"
              v-model="form.id"
              class="input-field"
              placeholder="사용하실 아이디를 입력해주세요"
            />
          </div>
          <div class="form-group password-group">
            <label for="password" class="form-label">비밀번호</label>
            <div class="input-with-icon">
              <input
                :type="form.showPassword ? 'text' : 'password'"
                id="password"
                v-model="form.password"
                class="input-field"
                placeholder="비밀번호를 입력해주세요"/>
              <i
                :class="{
                  'bi-eye': form.showPassword,
                  'bi-eye-slash': !form.showPassword,
                }"
                @click="togglePasswordVisibility"
                class="password-toggle-icon"
              ></i>
            </div>
          </div>
          <!-- 비밀번호 확인 필드 -->
          <div class="form-group password-group">
            <label for="confirmPassword" class="form-label"
              >비밀번호 확인</label>
            <div class="input-with-icon">
              <input
                :type="form.showConfirmPassword ? 'text' : 'password'"
                id="confirmPassword"
                v-model="form.confirmPassword"
                class="input-field"
                placeholder="비밀번호를 다시 입력해주세요"
              />
              <i
                :class="{
                  'bi-eye': form.showConfirmPassword,
                  'bi-eye-slash': !form.showConfirmPassword,
                }"
                @click="toggleConfirmPasswordVisibility"
                class="password-toggle-icon"
              ></i>
            </div>
          </div>
          <div class="form-group">
            <label for="name" class="form-label">이름</label>
            <input
              type="text"
              id="name"
              v-model="form.name"
              class="input-field"
              placeholder="이름을 입력해주세요"
            />
          </div>
          <div class="form-group">
            <label for="stageName" class="form-label">닉네임</label>
            <input
              type="text"
              id="stageName"
              v-model="form.stageName"
              class="input-field"
              placeholder="이름을 입력해주세요"
            />
          </div>
          <div class="form-group">
            <label for="email" class="form-label">이메일</label>
            <input
              type="email"
              id="email"
              v-model="form.email"
              class="input-field"
              placeholder="example@email.com"
            />
          </div>
          <div class="form-group">
            <label for="birthdate" class="form-label">생일</label>
            <input
              type="date"
              id="birthdate"
              v-model="form.birthdate"
              class="input-field"
            />
          </div>
          <div class="form-group">
            <label for="phone" class="form-label">휴대폰 번호</label>
            <input
              type="tel"
              id="phone"
              v-model="form.phone"
              class="input-field"
              placeholder="휴대폰 번호를 입력해주세요"
            />
          </div>
          <div class="form-group">
              <label for="image">이미지</label>
              <input type="file" id="image"  @change="onImageChange"  class="input-field" />
              <div v-if="selectedImage">
                <span @click="clearSelectedImage"> X</span></div>
                <img :src="selectedImage" v-if="selectedImage">
                
            </div>
        <div class="form-group">
            <label>성별</label>
            <select id="gender" v-model="gender" class="input-field">
            <option value="">성별을 선택하세요</option>
            <option value="M">남자</option>
            <option value="F">여자</option>
            </select>
        </div>
        <button :disabled="!isFormValid" class="btn-signup" @click="signup">
            회원가입
        </button>
        </form>
            </div>
        </div>
    </div>

</template>

<script setup>
import { ref, reactive, computed } from "vue";
import { useRouter } from 'vue-router'
import { memberApi } from "@/util/axios.js";

const router = useRouter();

const form = reactive({
  id: "",
  password: "",
  confirmPassword: "",
  name: "",
  email: "",
  stageName: "",
  birthdate: "",
  phone: "",
  showPassword: false,
  showConfirmPassword: false,
  gender: ref('M')
});
let image = null;
const selectedImage = ref(null);

const isEmailValid = (email) => {
  const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  return emailRegex.test(email);
};

const isFormValid = computed(() => {
  // 폼 유효성 검사 로직
  return (
    form.id &&
    form.password &&
    form.confirmPassword === form.password &&
    form.name &&
    isEmailValid(form.email) &&
    form.stageName &&
    form.birthdate &&
    form.phone &&
    form.gender
  );
});

const togglePasswordVisibility = () => {
  form.showPassword = !form.showPassword;
};

const toggleConfirmPasswordVisibility = () => {
  form.showConfirmPassword = !form.showConfirmPassword;
};

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


const signup = async () => {
  const newMember = {
    loginId: form.id,
    password: form.password,
    name: form.name,
    stageName: form.stageName,
    email: form.email,
    birth: form.birthdate,
    phone: form.phone,
    gender: form.gender,
  };
  const formData = new FormData();
for (const key in newMember) {
    formData.append(key, newMember[key]);
}
formData.append("profileImage", image)
  try {
    console.log(formData.get("loginId"))
    console.log(formData.get("password"))
    console.log(formData.get("name"))
    console.log(formData.get("stageName"))
    console.log(formData.get("email"))
    console.log(formData.get("birth"))
    console.log(formData.get("phone"))
    console.log(formData.get("gender"))
    const response = await memberApi.signup(formData);
    console.log(response)
    if (response.status === 200) {
      alert("회원가입 성공");
      router.push("/");
    }
  } catch (err) {
    console.error(err);
    alert("회원가입 실패");
    router.push("/");
  }
};

</script>

<style></style>