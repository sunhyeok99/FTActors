<template>
  <div>
    <video ref="videoPlayer" width="720" controls>
      <source :src="currentVideo" type="video/mp4">
      Your browser does not support the video tag.
    </video>
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue';
import { useRouter } from 'vue-router'
import axios from 'axios';


// 비디오가 끝났을 때 실행할 함수
const onVideoEnded = () => {
  videoIndex.value++; // 다음 비디오로 인덱스 증가
  if (videoIndex.value < videos.length) {
    currentVideo.value = videos[videoIndex.value]; // 다음 비디오로 변경
  } else {
    videoIndex.value = 0; // 인덱스를 처음으로 초기화
    currentVideo.value = videos[videoIndex.value]; // 첫 번째 비디오로 변경
  }
};


const BASE_URL = 'https://j10a602.p.ssafy.io/api/'
const router = useRouter();
const montages = ref([]);

// 비디오 파일 목록
const videos =[];
const videoIndex = ref(0); // 현재 비디오 인덱스
const currentVideo = ref(videos[0]); // 현재 비디오 URL
const videoPlayer = ref(null);


// 몽타쥬 리스트 가져오는 메서드
const getMontages = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/montage/list`);
    montages.value = response.data.data;
    console.log(response.data.data);
  } catch (error) {
    console.error(error);
  }
};

const goToMontageDetail = (montageId) => {
  router.push({ name: 'montageDetail', params: { id: montageId } });
};

onMounted(() => {
  // "ended" 이벤트 리스너 추가
  videoPlayer.value.addEventListener('ended', onVideoEnded);
});

// 컴포넌트가 언마운트될 때 이벤트 리스너 제거
watch(videoPlayer, (newValue, oldValue) => {
  if (oldValue) {
    oldValue.removeEventListener('ended', onVideoEnded);
  }
  if (newValue) {
    newValue.addEventListener('ended', onVideoEnded);
  }
}, { immediate: true });

</script>
