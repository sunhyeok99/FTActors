<template>
  <div class="container h-100">
    <div class="row justify-content-center align-items-start" style="min-height: 100vh;">
      <div class="col-md-6 col-lg-4">
        <div class="mt-5 pt-5">
          <div class="text-center mb-4">
            <h1><b>회원가입</b></h1>
          </div>
          <form @submit.prevent="signup">
            <div class="form-group mb-3">
              <label for="id" class="form-label">아이디</label>
              <input type="text" id="id" v-model="form.id" class="form-control" placeholder="사용하실 아이디를 입력해주세요">
            </div>
            <div class="form-group mb-3">
              <label for="password" class="form-label">비밀번호</label>
              <div class="passrow">
                <input :type="form.showPassword ? 'text' : 'password'" id="password" v-model="form.password"
                  class="form-control" placeholder="비밀번호를 입력해주세요">
                <span class="input-group-text" @click="togglePasswordVisibility">
                  <i :class="{ 'bi-eye': !form.showPassword, 'bi-eye-slash': form.showPassword }"></i>
                </span>
              </div>
            </div>
            <div class="form-group mb-3">
              <label for="confirmPassword" class="form-label">비밀번호 확인</label>
              <div class="passrow">
                <input :type="form.showConfirmPassword ? 'text' : 'password'" id="confirmPassword"
                  v-model="form.confirmPassword" class="form-control" placeholder="비밀번호를 다시 입력해주세요">
                <span class="input-group-text" @click="toggleConfirmPasswordVisibility">
                  <i :class="{ 'bi-eye': !form.showConfirmPassword, 'bi-eye-slash': form.showConfirmPassword }"></i>
                </span>
              </div>
            </div>
            <div class="form-group mb-3">
              <label for="name" class="form-label">이름</label>
              <input type="text" id="name" v-model="form.name" class="form-control" placeholder="이름을 입력해주세요">
            </div>
            <div class="form-group mb-3">
              <label for="email" class="form-label">이메일</label>
              <input type="email" id="email" v-model="form.email" class="form-control" placeholder="example@email.com">
            </div>
            <div class="form-group mb-3">
              <label for="birthdate" class="form-label">생일</label>
              <input type="date" id="birthdate" v-model="form.birthdate" class="form-control">
            </div>
            <div class="form-group mb-3">
              <label for="phone" class="form-label">휴대폰 번호</label>
              <input type="tel" id="phone" v-model="form.phone" class="form-control" placeholder="휴대폰 번호를 입력해주세요">
            </div>
            <div class="form-group mb-3">
              <label for="image" class="form-label">이미지</label>
              <input type="file" id="image" @change="onImageChange" class="form-control" />
              <div v-if="selectedImage">
                <span @click="clearSelectedImage"> X</span>
              </div>
              <img :src="selectedImage" v-if="selectedImage">
            </div>
            <div class="form-group mb-3">
              <label for="gender" class="form-label">성별</label>
              <div class="form-check mb-3">
                <input class="form-check-input" type="radio" v-model="form.gender" value="M" id="genderMale">
                <label class="form-check-label" for="genderMale">
                  남자
                </label>
              </div>
              <div class="form-check mb-3">
                <input class="form-check-input" type="radio" v-model="form.gender" value="F" id="genderFemale">
                <label class="form-check-label" for="genderFemale">
                  여자
                </label>
              </div>
            </div>
            <button :disabled="!isFormValid" class="btn btn-dark w-100 mb-2"ㄴ type="button" disabled @click.prevent="signup">
              <span class="spinner-border spinner-border-sm" aria-hidden="true"></span>
              <span role="status">회원가입</span>
            </button>
            <button type="button" class="btn w-100" @click="goToLogin">이미 회원이라면?</button>
          </form>
        </div>
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
  gender: "",
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
  if (file != null) {
    imageReader.onload = () => {
      selectedImage.value = imageReader.result;
    };
    imageReader.readAsDataURL(file);
    image = file;
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
  form.profileImage = null;
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
  // for (const key in newMember) {
  //     formData.append(key, JSON.stringify(newMember[key]));
  // }
  formData.append("dto", new Blob([JSON.stringify(newMember)], {
      type: "application/json"
  }));
  formData.append("profileImage", image)
  try {
    console.log(image)
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

const goToLogin = () => {
  router.push({ name: 'login' });
};

</script>

<style scoped>
.container h-100 {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

.form-control {
  margin-bottom: 1rem;
}

.passrow {
  display: flex;
  align-items: center;
  position: relative;
}

.input-group-text {
  cursor: pointer;
  margin-left: -40px;
  /* 아이콘 버튼을 입력 필드 내로 겹치게 하여 공간 활용 */
  border: 0;
  /* 테두리 제거 */
  background-color: transparent;
  /* 배경색 투명 */
}

.input-group-text i {
  font-size: 1rem;
  /* 아이콘 크기 조정 */
}
</style>
