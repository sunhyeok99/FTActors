<template>
  <div class="title">
    <h3><b>진행중인 공고 10개</b></h3>
    <button type="button" class="btn btn-primary" id="orderbtn">인기순</button>
    <button type="button" class="btn">내 맞춤 공고 보러가기✔</button>
    <button type="button" class="btn pageright" @click="goToBoardPage">▶️더보기</button>
  </div>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="board in recruitments" :key="board.id">
      <div class="card" id="board" @click="goToRecruitmentDetail(board.id)">
        <img :src="board.image || 'https://fs.joycity.com/index.asp'" class="img-fluid" style="width: 100%; height: auto;">
        <div class="card-body">
          <h5 class="card-title"><b>{{ board.title }}</b></h5>
          <p class="card-text">{{ board.endDate }} </p>
          <p class="dday">D-{{ calculateDday(board.endDate) }}</p>
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
import { recommendApi } from '@/util/axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router'

const router = useRouter();
const recruitments = ref([]);

const goToRecruitmentDetail = (recruitmentId) => {
  router.push({ name: 'boardDetail', params: { id: recruitmentId } });
};

const getList = async () => {
  try {
    await recommendApi.getRecruitmentList().then((res) => {
      recruitments.value = res.data;
      console.log(res.data)
    })   
  } catch (error) {
    console.error('Error fetching recruitment list:', error);
  }
};

onMounted(() => {
    getList();
});

// D-day 계산 함수
const calculateDday = (endDate) => {
  const today = new Date();
  const end = new Date(endDate);

  // 시간 차이를 밀리초로 계산한 후 일(day) 단위로 변환
  const difference = end - today;
  const dDay = Math.ceil(difference / (1000 * 60 * 60 * 24));

  return dDay;
};

const goToBoardPage = () => {
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
