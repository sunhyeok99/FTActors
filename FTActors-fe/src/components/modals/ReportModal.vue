<template>
  <div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"
    data-bs-backdrop="static" data-bs-keyboard="false">
    <div class="modal-dialog" @click.stop>
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">신고</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          신고사유
          <div class="form-floating">
            <select class="form-select" id="floatingSelect" aria-label="Floating label select example" ref="reasonSelect">
              <option value="0" selected>구분</option>
              <option value="1">타인을 비방하거나, 모욕함</option>
              <option value="2">감독의 권한을 남용함</option>
              <option value="3">사용 목적에 맞지 않는 사용자</option>
            </select>   
            <label for="floatingSelect">선택</label>
          </div>
          <div class="form-floating">
            <textarea class="form-control" placeholder="신고 내용" id="floatingTextarea2" style="height: 100px"  ref="detailText"></textarea>
            <label for="floatingTextarea2">세부 내용</label>
          </div>
          <div class="input-group">
            <input type="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04"
              aria-label="Upload">
            <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">업로드</button>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" @click="uploadReport">신고 보내기</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>

        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {  onMounted, ref } from 'vue';
import axios from 'axios';

const reasonSelect = ref(null);
const detailText = ref(null)
const props = defineProps({
  currentId: Number
});
console.log('커런트아이디',props.currentId)
const uploadReport = () => {
  const formData = new FormData();
  // 신고 사유 추가
  if (reasonSelect.value && reasonSelect.value.value) {
    formData.append('reason', reasonSelect.value.value);
  }
  // 세부 내용 추가
  if (detailText.value) {
    formData.append('details', detailText.value.value);
  }
  // 파일 추가
  const fileInput = document.querySelector('input[type="file"]');
  if (fileInput && fileInput.files[0]) {
    formData.append('file', fileInput.files[0]);
  }

  axios.post(`http://localhost:8080/montage/${props.currentId}/report`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
  .then(response => {
    console.log(response.data);
  })
  .catch(error => {
    console.error(error);
  });
};


onMounted(() => {
  props.currentId;
});

</script>

<style></style>