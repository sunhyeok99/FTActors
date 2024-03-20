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
                    <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
          <li class="list-group-item"><label><b>이름</b></label> 배사람 </li>
          <li class="list-group-item"><label><b>닉네임</b></label>춤추는 산양 </li>
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
      <p>안녕하세요 배우 안기영입니다 :)
        항상 맡은 바 책임을 다하는 배우가 되겠습니다.
  
        이름 : 안기영
        나이 : 27세 (1996.12.08)
        신장 : 180cm / 65kg
        학력 : 서울예대 연기전공 졸업
        특기 : 액션, 춤, 악기연주(기타/플룻), 노래
        연락처 : 01076777064 / gi0miso@naver.com /
        https://www.instagram.com/hug.0a/
  
        연기영상 : https://youtu.be/R0tEXCYsBt4
        https://youtu.be/R0tEXCYsBt4</p>
  
      <h4><b>학력</b></h4>
      <p>서울예술대학교 연기전공</p>
      <h4><b>경력</b></h4>
      <p></p>
      <h4><b>추가 이미지</b></h4>
      <p></p>
      <h4><b>연기영상</b></h4>
      <p></p>
    </div>
  
  
  </template>
  <script setup>
  import { useRouter } from 'vue-router'
  
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