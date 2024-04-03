<template>
    <div class="title"><h3><b>진행중인 공고 10개</b></h3>
    <button type="button" class="btn btn-primary" id="orderbtn">인기순</button>
    <button type="button" class="btn">내 맞춤 공고 보러가기✔</button>
    <button type="button" class="btn pageright" @click="goToBoardPage">▶️더보기</button>
  </div>
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col">
        <div class="card" id="board">
          <img src="@/assets/board/b1.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title"><b>웹드라마'씨타입'채널에서 배우님을 모십니다</b></h5>
            <p class="card-text">D-9 / 2024-03-15 마감</p>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card" id="board">
          <img src="@/assets/board/b2.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title"><b>OTT 드라마 <탁류>에서 액션잘하는 여자배우님을 캐스팅 합니다</b></h5>
            <p class="card-text">D-9 / 2024-03-15 마감</p>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card" id="board">
          <img src="@/assets/board/b3.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title"><b>유튜브 '이해인'채널 섹시코미디 배우 모집</b></h5>
            <p class="card-text">D-9 / 2024-03-15 마감</p>
          </div>
        </div>
      </div>
      <div class="col">
        <div class="card" id="board">
          <img src="@/assets/board/b4.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title"><b>[부산] 연극 <달동네> 전배역 오디션</b></h5>
            <p class="card-text">D-9 / 2024-03-15 마감</p>
          </div>
        </div>
      </div>

    </div>
  </template>
  
  <script setup>
    import { useRouter } from 'vue-router'
const router = useRouter();
const goToBoardPage=() => {
  router.push({ name: 'board' });
};
  </script>
  <style>
#board {
    border: none;
}
.title {
  display: flex;
}

#orderbtn {
  border-radius: 25px;
  /* 그라데이션 적용 */
  background-image: linear-gradient(to right, rgb(58, 123, 213), rgb(39, 16, 171));
  border: none; /* 기존 border 설정을 수정 */
}

.pageright {
  display: flex;
  margin-left: auto;
}
</style>