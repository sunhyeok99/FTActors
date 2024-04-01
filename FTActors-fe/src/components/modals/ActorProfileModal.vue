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
                      <!-- <button class="btn btn-outline-secondary dropdown-toggle" type="button"
                          data-bs-toggle="dropdown" aria-expanded="false">Dropdown</button> -->
                      <!-- <ul class="dropdown-menu">
                          <li><a class="dropdown-item" href="#">Action</a></li>
                          <li><a class="dropdown-item" href="#">Another action</a></li>
                          <li><a class="dropdown-item" href="#">Something else here</a></li>
                          <li>
                              <hr class="dropdown-divider">
                          </li>
                          <li><a class="dropdown-item" href="#">Separated link</a></li>
                      </ul> -->
                
                      <input type="text" class="form-control" aria-label="Text input with dropdown button" placeholder="경력사항">
                  </div>
              </div>
              <div class="modal-footer">
                  <button class="btn btn-secondary" data-bs-target="#actorModalToggle2" data-bs-toggle="modal">
                      다음으로</button>
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
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">닫기</button>
                <button class="btn btn-secondary" data-bs-target="#actorModalToggle1"
                    data-bs-toggle="modal">이전으로</button> <!-- @#$ 왜???? 여기서만 1로 안 돌아가고 꺼짐-->

                  <button class="btn btn-secondary" data-bs-target="#actorModalToggle3"
                      data-bs-toggle="modal">다음으로</button>
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
                              id="inputGroupFileAddon04">업로드</button> <!-- @#$ null 허용이니까, 업로드 안할 땐 건너뛰기로 바꾸고, 업로드하면 4단계로 바꾸기-->
                      </div>
                  </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">닫기</button>
                <button class="btn btn-secondary" data-bs-target="#actorModalToggle2"
                    data-bs-toggle="modal">이전으로</button>

                  <button class="btn btn-secondary" data-bs-target="#actorModalToggle4"
                      data-bs-toggle="modal">다음으로</button>
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
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">닫기</button>
                <button class="btn btn-secondary" data-bs-target="#actorModalToggle3"
                    data-bs-toggle="modal">이전으로</button>

                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">프로필 생성</button>
              </div>
          </div>
      </div>
  </div>


  <!-- 토글 버튼 -->
  <button class="btn btn-primary" data-bs-target="#actorModalToggle" data-bs-toggle="modal"> 배우 프로필 만들기</button>
</template>

<script setup>

// $(function () {
//     $('input[name="birthday"]').daterangepicker({
//         singleDatePicker: true,
//         showDropdowns: true,
//         minYear: 1901,
//         maxYear: parseInt(moment().format('YYYY'), 10)
//     }, function (start, end, label) {
//         var years = moment().diff(start, 'years');
//         // alert("You are " + years + " years old!");
//     });
// });


</script>

<style></style>