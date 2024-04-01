<template>
  <div class="profileheader">
    <h1> <b>PROFILE</b></h1>
    </div>
    <div class="profilepage">
      <img src="@/assets/actors/소희.jpg" alt="">
      <div class="profilelist">
        <ul class="list-group list-group-flush">
          <li class="list-group-item"><label><b>이름</b></label>배사람 </li>
          <div class="accordion" id="accordionPanelsStayOpenExample">
            <div class="accordion-item">
              <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                 전화번호
                </button>
              </h2>
              <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse">
                <div class="accordion-body">
                  <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="전화번호 수정" aria-label="Recipient's username" aria-describedby="button-addon2">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">수정</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
          <li class="list-group-item"><label><b>이름</b></label> 배사람 </li>
          <li class="list-group-item"><label><b>닉네임</b></label>우아아악 </li>
          <li class="list-group-item"><label><b>이메일</b></label> bausa@gmail.com </li>
          <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
          <li class="list-group-item"><label><b>이름</b></label> 배사람 </li>
          <li class="list-group-item"><label><b>닉네임</b></label>춤추는 산양 </li>
          <li class="list-group-item"><label><b>이메일</b></label> bausa@gmail.com </li>
          <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
        </ul>
      </div>
    </div>
    <div class="detailprofilepage">
      <h4><b>자기소개</b></h4>
      <div class="form-floating">
        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" v-model="introduction"></textarea>
        <label for="floatingTextarea2">Comments</label>
      </div>
     

      <h4><b>경력</b></h4>
      <div class="form-floating">
        <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
        <label for="floatingTextarea">Comments</label>
      </div>
      <h4><b>추가 이미지</b></h4>
      <div class="input-group">
        <input type="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
        <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">업로드</button>
      </div>
      <h4><b>연기영상</b></h4>
      <div class="input-group">
        <input type="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
        <button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">업로드</button>
      </div>
    </div>
  
  
  </template>
  <script setup>
    import { ref } from 'vue'
  import { useRouter } from 'vue-router'
  const introduction = ref('안녕하세요 배우 안기영입니다 :) 항상 맡은 바 책임을 다하는 배우가 되겠습니다.')
  const school = ref('한예종')
  
  </script>
  <style scoped>
  h1 {
    font-size: 4rem;
  }
  
  .profileheader {
    display: flex;
    align-items: center;
    font-size: 4rem;
  }
  
   .profiletab {
    height: 1rem;
    margin-left: auto;
  } 
  
  .profiletab p {
    margin-bottom: 0;
  }
  .profiletab img {
    width: 24px;
    height: 24px;
  
  }
  
  .profilepage {
    display: flex;
  }
  
  .profilelist {
    width: 30rem;
  }
  
  .detailprofilepage {
    width: 50%;
  }
  .form-floating {
    display: flex;
    
  }
  .form-floating button {
    width: 70px;
  }

  
  </style>