  <template>
  <div class="masonry" style="--bs-columns: 4;">
    <div class="masonry-item" v-for="board in boards" :key="board.id">
      <div class="card"  @click="goToBoardDetail(board.id)">
        <img :src="board.image" @error="setDefaultImage" alt="" class="img-fluid">
              <button class="like-btn" @click.stop="toggleLike(board.id)">
                <img v-if="board.wishList === 1" src="@/assets/icons/like-filled.png" alt="Liked">
                <img v-else src="@/assets/icons/like-outline.png" alt="Like">         
              </button>
              
            <div class="col-md-8">
              <div class="card-body" @click="goToBoardDetail(board.id)">
                <h5 class="card-title"><b>{{ board.title }}</b></h5>
                <p class="card-text">{{ board.content }}</p>
                <p class="card-text">{{ board.endDate }} </p><p class="dday">D-{{ calculateDday(board.endDate) }}</p>
              </div>
            </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { recruitmentApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";
  
const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
const adminId = 11;

const router = useRouter();
const boards = ref([]);

// getList 함수 정의: 백엔드로부터 공고 리스트를 받아오는 함수
const getList = async (memberId) => {
  try {
    await recruitmentApi.getList(memberId).then((res) => {
      boards.value = res.data.data;
    })   
  } catch (error) {
    console.error('Error fetching recruitment list:', error);
  }
};
const goToBoardDetail = (boardId) => {
  router.push({ name: 'boardDetail', params: { id: boardId } });
};
const toggleLike = async (index) => {
  try {
    if(loginMember.value =="" || loginMember.value == null){
      alert(" 로그인이 필요합니다")
      router.push({ name: 'login' });
    }
    else{
      const memberId = loginMember.value; 
      const recruitmentId = index;
      const response = await recruitmentApi.updateWishlist(recruitmentId, memberId);
      if(response.data.status == 200){
        if(response.data.data == 0){
          alert('찜 목록에서 삭제되었습니다.')
        }
        else{
          alert('찜 등록이 완료되었습니다.')
        }
        const tmp = boards.value.find(board => board.id === index);
        tmp.wishList = response.data.data;
        console.log(tmp.wishList)
      }
    }
    } catch (error) {
      console.error('Error toggling like:', error);
  }
};

// D-day 계산 함수
const calculateDday = (endDate) => {
  const today = new Date();
  const end = new Date(endDate);

  // 시간 차이를 밀리초로 계산한 후 일(day) 단위로 변환
  const difference = end - today;
  const dDay = Math.ceil(difference / (1000 * 60 * 60 * 24));

  return dDay;
};
const setDefaultImage = (event) => {
  event.target.src = "@/assets/icons/NoImage.png"; // 경로는 프로젝트 설정에 따라 조정
};

// 페이지가 로드될 때 getList 함수 호출
onMounted(() => {
  if(loginMember.value == "" || loginMember.value == null){
    getList(adminId);
  }
  else{
    getList(loginMember.value);
  }
});



</script>

<style scoped>


.image-container {
  position: relative;
  display: inline-block;
}

.card-img-top {
  width: 100%;
  display: block;
}

.like-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: transparent;
  border: none;
  cursor: pointer;
}

.liked img {
  filter: invert(36%) sepia(94%) saturate(3013%) hue-rotate(346deg) brightness(100%) contrast(97%);
}

.like-btn img {
  width: 20px;
  height: 20px;
}
.masonry {
  column-count: var(--bs-columns);
  column-gap: 1.5rem;
}

.masonry-item {
  break-inside: avoid;
  margin-bottom: 1.5rem;
}
@media (max-width: 992px) {
  .masonry {
    --bs-columns: 3;
  }
}

@media (max-width: 768px) {
  .masonry {
    --bs-columns: 2;
  }
}

@media (max-width: 576px) {
  .masonry {
    --bs-columns: 1;
  }
}
</style>
