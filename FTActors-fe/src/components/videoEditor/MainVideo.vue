<template>
  <div class="container-fluid">
    <div class="row">
      <!-- 왼쪽에 배치된 div -->
      <div class="col-md-6">
        <h3>영상 리스트</h3>
        <div class="row row-cols-1 row-cols-md-2 g-4">
          <div class="col" v-for="apply in applys" :key="apply.id" @click="toggleVideos(apply.id)">
            <div class="card" id="apply">
              <video class="video" controls preload>
                <source :src="apply.videoLink" type="video/mp4" />
              </video>
              <h5 class="card-title"><b>{{ apply.memberName }}</b></h5>
              <p>{{ truncateVideoName(apply.videoName) }}</p>
              <p class="card-text">{{ apply.createdAt }}</p>
            </div>
          </div>
        </div>
      </div>

      <!-- 오른쪽에 배치된 div -->
      <div class="col-md-6">
        <h3>선택된 영상</h3>
        <div v-if="selectedApplyIds.length > 0" class="row row-cols-1 g-4">
          <div class="col" v-for="video in filteredVideos" :key="video.id" @click="toggleVideos(video.id)">
            <div class="card" id="video">
              <video class="video" controls preload>
                <source :src="video.videoLink" type="video/mp4" />
              </video>
              <h5 class="card-title"><b>{{ video.memberName }}</b></h5>
              <p class="card-text">{{ video.createdAt }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 하단에 선택된 영상 개수 표시 -->
    <p>선택된 영상 개수: {{ selectedApplyIds.length }}</p>

    <!-- 영상 편집하려 이동하는 버튼 -->
    <button @click="navigateToVideoEditingPage" :disabled="selectedApplyIds.length === 0">
      영상 편집하러 가기
    </button>
  </div>
</template>

<script setup>
import { ref, onMounted, computed  } from 'vue';
import { useRouter } from 'vue-router';
import { recruitmentApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";


const router = useRouter();
const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
// const recruitmentId = router.currentRoute.value.params.id;

// import { videoApi } from '@/util/axios';
// import { useRoute, useRouter } from "vue-router";


// const applys = ref([]);
const videos = ref([]);
const applys = ref([
  { videoLink: '/src/assets/montage/콘트라베이스.mp4' },
  { videoLink: '/src/assets/montage/핸드크림.mp4' },
  { videoLink: '/src/assets/montage/담요.mp4' }
]);
const selectedApplyIds = ref([]);

// import { recruitmentApi } from '@/util/axios';
// import { useMemberStore } from "@/stores/member-store.js";
  
// const MemberStore = useMemberStore();
// const loginMember = ref(null);
// loginMember.value = MemberStore.memberInfo;
// const adminId = 11;

// const router = useRouter();
// const boards = ref([]);

// // getList 함수 정의: 백엔드로부터 공고 리스트를 받아오는 함수
// const getList = async (memberId) => {
//   try {
//     await recruitmentApi.getList(memberId).then((res) => {
//       boards.value = res.data.data;
//     })   
//   } catch (error) {
//     console.error('Error fetching recruitment list:', error);
//   }
// };


const recruitmentId = ref(2);
//const videoFiles = ref([])
onMounted(
  async ()=>{
    await videoApi.getAllPerformVideo(recruitmentId.value)
    .then((res) => {
      console.log(res)
      console.log(res.data.data);
      videoList.value = res.data.data;
    })  
  }
)



// getList 함수 정의: 백엔드로부터 공고 리스트를 받아오는 함수
const getList = async (recruitmentId) => {
  try {
    await recruitmentApi.getApplyList(recruitmentId).then((res) => {
      // applys.value = res.data.data;
      videos.value = res.data.data;
    })   
  } catch (error) {
    console.error('Error fetching recruitment list:', error);
  }
};

// 페이지가 로드될 때 getList 함수 호출
onMounted(() => {
  console.log(recruitmentId)
  getList(recruitmentId);
});

// apply를 클릭하면 해당 apply의 id를 selectedApplyIds에 추가하거나 삭제
const toggleVideos = (applyId) => {
  console.log(applyId)
if (isSelectedApply(applyId)) {
  console.log("포함되있었음")
  selectedApplyIds.value = selectedApplyIds.value.filter(id => id !== applyId);
} else {
  console.log('포함안되어있었음')
  if (selectedApplyIds.value.length < 5) {
    selectedApplyIds.value.push(applyId);
  }
}
};

// 선택된 apply인지 확인하는 메서드
const isSelectedApply = (applyId) => {
return selectedApplyIds.value.includes(applyId);
};

// 선택된 apply에 해당하는 영상들을 필터링
const filteredVideos = computed(() => {
return videos.value.filter(video => selectedApplyIds.value.includes(video.id));
});

// 영상 편집 페이지로 이동하는 메서드
const navigateToVideoEditingPage = () => {
if (selectedApplyIds.value.length > 1) {
  router.push({ name: 'editPage', params: { params: { id: selectedApplyIds.value } } });
} else {
  alert('최소 2개의 영상을 선택해주세요.');
}
};

const truncateVideoName = (name) => {
if (!name) return ''; // name이 정의되어 있지 않으면 빈 문자열 반환
for (let i = 0; i < name.length; i++) {
  if (name.substring(i, i + 1) === "_") {
    return name.substring(i + 1, name.length);
  }
}
return name;
};

</script>

<style scoped>
#board {
  border: none;
}

.image-container {
  position: relative;
  display: inline-block;
}

.card-img-top {
  width: 100%;
  /* Adjust the size as needed */
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
.card {
  width: 100%;
  margin-bottom: 20px; /* Adjust as needed */
}
</style>
