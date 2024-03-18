

<template>
    <div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
      <div class="modal-dialog" @click.stop>
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">신고</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            신고사유
            <div class="form-floating">
                <select class="form-select" id="floatingSelect" aria-label="Floating label select example">
                  <option selected>구분</option>
                  <option value="1">타인을 비방하거나, 모욕함</option>
                  <option value="2">감독의 권한을 남용함</option>
                  <option value="3">사용 목적에 맞지 않는 사용자</option>
                </select>
                <label for="floatingSelect">Works with selects</label>
              </div>
              <div class="form-floating">
                <textarea class="form-control" placeholder="신고 내용" id="floatingTextarea2" style="height: 100px"></textarea>
                <label for="floatingTextarea2">세부 내용</label>
              </div>
              <div class="input-group">
                <input type="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">업로드</button>
              </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary">신고 보내기</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
           
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  </script>
  
  <style>
  
  </style>