<template>
  <div class="sch-container">
    <Role></Role>

    <!-- 동영상 목록 -->
    <VideoSideBar :videoList="videoFiles" class="item" @videoSelected="handleVideoSelected" />

    <!-- 두 번째 동영상 목록 -->
    <VideoSideBar :videoList="videoFiles" class="item" @videoSelected="handleVideoSelected2" />

    <!-- 첫 번째 동영상 플레이어 -->
    <div class="container-editor2">
      <video ref="videoRef" controls class="player-video"></video>
      <button @click="playVideo">재생</button>
      <button @click="cancelVideo">취소</button> 
    </div>

    <!-- 두 번째 동영상 플레이어 -->
    <div class="container-editor3">
      <video ref="videoRef2" controls class="player-video"></video>
      <button @click="playVideo2">재생</button>
      <button @click="cancelVideo2">취소</button>
    </div>
  </div>
  <SendButton></SendButton>
</template>

<script setup>
import { ref } from 'vue';
import VideoSideBar from './VideoSideBar.vue';

const videoFiles = ref([
  { src: '/src/assets/montage/콘트라베이스.mp4' },
  { src: '/src/assets/montage/핸드크림.mp4' },
  { src: '/src/assets/montage/담요.mp4' }
]);

// 첫 번째 동영상 플레이어 선택한 비디오의 인덱스
const selectedVideoIndex = ref(null);

// 첫 번째 동영상 플레이어 비디오 선택 핸들러
const handleVideoSelected = (index) => {
  if (selectedVideoIndex.value === index) {
    selectedVideoIndex.value = null; // 같은 비디오를 다시 클릭했을 때 선택 취소
  } else {
    selectedVideoIndex.value = index;
  }
};

// 첫 번째 동영상 플레이어 비디오 플레이 핸들러
const playVideo = () => {
  console.log(selectedVideoIndex.value)
  if (selectedVideoIndex.value !== null) {
    console.log(selectedVideoIndex.value)
    const videoRef = document.querySelector('.container-editor2 video');
    console.log(videoRef)
    console.log(videoRef.classList);
    if (videoRef) {
      console.log(selectedVideoIndex.value)
      const selectedVideo = videoFiles.value[selectedVideoIndex.value];
      if (videoRef.src !== selectedVideo.src) {
        videoRef.src = selectedVideo.src;
        videoRef.load();
        console.log(videoRef)
      } else {
        videoRef.src = ''; // 이미 선택된 비디오를 다시 클릭한 경우 비디오 제거
        videoRef.load(); // 비디오를 제거한 후에는 로드를 다시 호출해야 합니다.
      }
      if (!videoRef.paused) {
        videoRef.pause(); // 영상이 이미 재생 중인 경우에는 일시 정지
      } else {
        videoRef.play(); // 영상 재생
      }
    }
  }
};

// 첫 번째 동영상 플레이어 비디오 취소 핸들러
const cancelVideo = () => {
  const videoRef = document.querySelector('.container-editor2 video');
  if (videoRef) {
    videoRef.src = ''; // 플레이어에 업로드된 영상 삭제
    console.log("zxcv")
    videoRef.load(); // 비디오를 제거한 후에는 로드를 다시 호출해야 합니다.
  }
};

// 두 번째 동영상 플레이어 선택한 비디오의 인덱스
const selectedVideoIndex2 = ref(null);

// 두 번째 동영상 플레이어 비디오 선택 핸들러
const handleVideoSelected2 = (index) => {
  if (selectedVideoIndex2.value === index) {
    selectedVideoIndex2.value = null; // 같은 비디오를 다시 클릭했을 때 선택 취소
  } else {
    console.log("asdf");
    selectedVideoIndex2.value = index;
  }
};

// 두 번째 동영상 플레이어 비디오 플레이 핸들러
const playVideo2 = () => {
  try {
    
    console.log("playVideo2")
    console.log(selectedVideoIndex2.value)
    if (selectedVideoIndex2.value !== null) {
      console.log(selectedVideoIndex2.value)
      const videoRef2 = document.querySelector('.container-editor3 video');
      console.log(videoRef2)
      console.log(videoRef2.classList);
      if (videoRef2) {
        const selectedVideo2 = videoFiles.value[selectedVideoIndex2.value];
        if (videoRef2.src !== selectedVideo2.src) {
          videoRef2.src = selectedVideo2.src;
          videoRef2.load();
        } else {
          videoRef2.src = ''; // 이미 선택된 비디오를 다시 클릭한 경우 비디오 제거
          videoRef2.load(); // 비디오를 제거한 후에는 로드를 다시 호출해야 합니다.
        }
        if (!videoRef2.paused) {
          videoRef2.pause(); // 영상이 이미 재생 중인 경우에는 일시 정지
        } else {
          videoRef2.play(); // 영상 재생
        }
      }
    }
  } catch (error) {
    console.error('playVideo2 함수에서 오류 발생:', error);
  }
};

// 두 번째 동영상 플레이어 비디오 취소 핸들러
const cancelVideo2 = () => {
  console.log("qwer");
  const videoRef2 = document.querySelector('.container-editor3 video');
  if (videoRef2) {
    videoRef2.src = ''; // 플레이어에 업로드된 영상 삭제
    videoRef2.load(); // 비디오를 제거한 후에는 로드를 다시 호출해야 합니다.
  }
};
</script>

<style scoped>
.sch-container {
  display: flex;
  height: 100vh;
  width: 100%;
  flex-direction: row;
}

.container-editor2 {
  flex: 3;
}

.container-editor3{
  flex:3;
}

.player-video {
  width: 100%; /* 플레이어의 너비에 맞게 비디오 크기 조정 */
  height: 60%; /* 비디오의 높이를 비율에 맞게 조정 */
}

</style>
