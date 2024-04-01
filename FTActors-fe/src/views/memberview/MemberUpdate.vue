<template>
    <div class="row">
        <div class="col-md-3">
            <div>
                <h1><b>멤버 추가 정보 입력</b></h1>
            </div>
            <div>
                <form>
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
          <!-- 유저 이미지 선택 버튼 -->
   

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
import { useMemberStore } from "@/stores/member-store.js";
  
  const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;

const router = useRouter();

const form = reactive({
  name: "",
  email: "",
  birthdate: "",
  phone: "",
  gender: 'M'
});

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
    form.birthdate &&
    form.phone &&
    form.gender
  );
});

const signup = async () => {
  const newMember = {
    id: loginMember.id,
    name: form.name,
    email: form.email,
    birthdate: form.birthdate,
    number: form.phone,
    gender: from.gender,
  };
  try {
    const response = await memberApi.updatePassword(newMember);
    if (response.status === 200) {
      alert("정보 변경 성공");
      router.push("/");
    }
  } catch (err) {
    console.error(err);
    alert("정보 변경 실패");
  }
};





</script>

<style></style>