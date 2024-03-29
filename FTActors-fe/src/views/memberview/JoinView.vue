<template>
    <div class="row">
        <div class="col-md-3">

            <div>
                <h1><b>회원가입</b></h1>
            </div>
            <div>
                <form>

                    <div class="form-group">
                        <label>아이디</label>
                        <input type="text" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>비밀번호</label>
                        <input type="password" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>비밀번호 재확인</label>
                        <input type="password" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>이메일</label>
                        <input type="text" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>연락처</label>
                        <input type="text" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label>생년월일</label>
                        <input type="text" name="birthday" value="" />
                    </div>
                    <div class="form-group">

                         <label>성별</label>
                         <div class="form-check">
                            <input type="radio" class="form-check-input" id="validationFormCheck2" name="radio-stacked" required>
                            <label class="form-check-label" for="validationFormCheck2">남(He/Him)</label>
                          </div>
                          <div class="form-check">
                            <input type="radio" class="form-check-input" id="validationFormCheck3" name="radio-stacked" required>
                            <label class="form-check-label" for="validationFormCheck3">여(She/Her)</label>
                          </div>
                          <div class="form-check mb-3">
                            <input type="radio" class="form-check-input" id="validationFormCheck3" name="radio-stacked" required>
                            <label class="form-check-label" for="validationFormCheck3">기타(They/Them)</label>
                          </div>
                    </div>
                    <div class="form-group">
                        <label>닉네임</label>
                        <input type="text" class="form-control" />
                    </div>
                    <div class="my-3">
                        <button type="submit" class="btn btn-primary">회원가입</button>
                    </div>
                </form>
            </div>
            <button type="button" class="btn" @click="goToLogin">이미 회원이라면?</button>



        </div>
    </div>

</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const goToLogin = () => {
    router.push({ name: 'login' });
};



$(function () {
    $('input[name="birthday"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'), 10)
    }, function (start, end, label) {
        var years = moment().diff(start, 'years');
        // alert("You are " + years + " years old!");
    });
});

</script>

<style></style>