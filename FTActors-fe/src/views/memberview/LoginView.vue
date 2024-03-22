<template>
  <div class="row">
    <div class="col-md-3">
      <div>
        <div>
          <h1><b>로그인</b></h1>
        </div>
        <form>
          <div class="form-group">
            <label>아이디</label>
            <input type="text" class="form-control" />
          </div>
          <div class="form-group">
            <label>비밀번호</label>
            <input type="password" class="form-control" />
          </div>
          <div class="my-3 logins">
            <button type="submit" class="btn btn-primary">로그인</button>
            <img class="kakaologin" src="@/assets/icons/KakaoLogin.png" alt="">
           
          </div>
        </form>
      </div>

      <button type="button" class="btn" @click="goToJoin">아직 회원이 아니라면?</button>

    </div>
  </div>

</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const goToJoin = () => {
  router.push({ name: 'join' });
};

</script>
<style>
.logins{
  display: flex;
  justify-content: space-between;
}
.kakaologin{
  cursor: pointer;
}

</style>