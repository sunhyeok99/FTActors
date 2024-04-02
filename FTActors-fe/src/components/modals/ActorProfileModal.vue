<template>
  <!-- 1단계 -->
  <div class="modal fade" id="actorModalToggle" aria-hidden="true" aria-labelledby="actorModalToggleLabel"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="actorModalToggleLabel">1단계 경력</h1>
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
                  <button class="btn btn-secondary" data-bs-target="#actorModalToggle2" data-bs-toggle="modal">
                      2단계</button>
              </div>
          </div>
      </div>
  </div>

  <!-- 2단계 -->
  <div class="modal fade" id="actorModalToggle2" aria-hidden="true" aria-labelledby="actorModalToggleLabel2"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="actorModalToggleLabel2">2단계 자기소개</h1>
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
                  <button class="btn btn-secondary" data-bs-target="#actorModalToggle3"
                      data-bs-toggle="modal">3단계</button>
              </div>
          </div>
      </div>
  </div>
  <!-- 3단계 -->
  <div class="modal fade" id="actorModalToggle3" aria-hidden="true" aria-labelledby="actorModalToggleLabel3"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="actorModalToggleLabel3">3단계 포트폴리오 업로드</h1>
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
                  <button class="btn btn-secondary" data-bs-target="#actorModalToggle4"
                      data-bs-toggle="modal">4단계</button>
              </div>
          </div>
      </div>
  </div>
  <!-- 4단계 -->
  <div class="modal fade" id="actorModalToggle4" aria-hidden="true" aria-labelledby="actorModalToggleLabel4"
      tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h1 class="modal-title fs-5" id="actorModalToggleLabel4">4단계 추가 사진 업로드</h1>
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
                <button type="button" class="btn btn-dark" data-bs-dismiss="modal">프로필 생성</button>
              </div>
          </div>
      </div>
  </div>


  <!-- 토글 버튼 -->
  <button class="btn btn-dark" data-bs-target="#actorModalToggle" data-bs-toggle="modal">감독 프로필 만들기</button>
</template>

<script setup>

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