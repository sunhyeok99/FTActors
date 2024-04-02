<script setup>
import { ref, watch, onMounted, onBeforeUnmount, provide } from "vue";
import { createFFmpeg, fetchFile } from '@ffmpeg/ffmpeg';
import VideoSideBar from "./VideoSideBar.vue";
// import Editor from "./Editor.vue"
import Editor from "./Editor2.vue"

// import { createFFmpeg, fetchFile } from "npm:@ffmpeg/ffmpeg";
import { join, dirname } from 'node:path';



const videoFiles = ref([
  { src: '/src/assets/montage/콘트라베이스.mp4' },
//   { src: '/src/assets/montage/핸드크림.mp4' },
//   { src: '/src/assets/montage/담요.mp4' }
]);

const selectedVideoIndex = ref(null);

const handleVideoSelected = (index) => {
  selectedVideoIndex.value = index;
  console.log("selected video index from Main !! ", selectedVideoIndex.value);
};


// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


// const ffmpeg = new FFmpeg();

// const FFmpegCoreLocation = join(
//   dirname(import.meta.url).replace(/^file:\/\/\//, ''),
//   '../../@ffmpeg/core/dist/umd/ffmpeg-core.js'
// );

// const FFmpegWasmLocation = join(
//   dirname(import.meta.url).replace(/^file:\/\/\//, ''),
//   '../../@ffmpeg/core/dist/umd/ffmpeg-core.wasm'
// );

// const ffmpeg = createFFmpeg({
//   corePath: FFmpegCoreLocation,
//   wasmPath: FFmpegWasmLocation,
// });

const ffmpeg = createFFmpeg({ log: true });

const videoRef = ref(null);

let startTime = ref(0); // 시작 시간을 저장할 변수
let endTime = ref(0);   // 종료 시간을 저장할 변수

const loadFFmpeg = async () => {
    console.log("loadFFmpeg ======== ffmpeg.load() 시작");
  await ffmpeg.load();
  console.log("loadFFmpeg ======== ffmpeg.load() 완료");
};

const playSegment = async () => {
  // FFmpeg 로드
  await loadFFmpeg();
  console.log("playSegment ======== ffmpeg.load() 완료");
  
  // 시작 시간과 종료 시간을 이용하여 자를 길이 계산
  const duration = endTime.value - startTime.value;

  // FFmpeg를 사용하여 동영상을 자르고 저장하는 작업 수행
  const segmentFileName = 'segment.mp4';
  await ffmpeg.exec([
    '-i', 'input.mp4',
    '-ss', `${startTime.value}`, // 시작 시간
    '-t', `${duration}`,         // 자를 길이
    '-c', 'copy',
    segmentFileName
  ]);


  // 자른 동영상을 비디오 태그에 로드하여 재생
  const video = videoRef.value;
  video.src = segmentFileName;
  video.load();
  video.play();
};


// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

const currentTime = ref(0);

const updateCurrentTime = () => {
  if (videoElement.value) {
    currentTime.value = videoElement.value.currentTime;
  }
};

const saveCurrentTime = (type) => {
  if (type === 'start') {
    startTime.value = currentTime.value;
  } else if (type === 'end') {
    endTime.value = currentTime.value;
  }
  console.log("Current Time:", currentTime.value);
};

// 비디오 요소 참조
const videoElement = ref(null);

const clearTimes = () => {
  startTime.value = 0;
  endTime.value = 0;
};

onMounted(() => {
  const video = videoRef.value;
  video.addEventListener('timeupdate', updateCurrentTime);
});

onBeforeUnmount(() => {
  const video = videoRef.value;
  video.removeEventListener('timeupdate', updateCurrentTime);
});

// onMounted(() => {
//   videoElement.value.addEventListener('timeupdate', updateCurrentTime);
// });

// onBeforeUnmount(() => {
//   videoElement.value.removeEventListener('timeupdate', updateCurrentTime);
// });

const handleVideoPlay = () => {
  currentTime.value = 0;
  videoElement.value = document.querySelector('.video');
};

// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


</script>

<template>
  <div class="sch-container">
    <Role></Role>
    <!-- <VideoSideBar :videoList="videoFiles" class="item" @videoSelected="handleVideoSelected" />
    <Editor2 :videoList="videoFiles" class="item" :selectedVideoIndex="selectedVideoIndex" /> -->

    <!-- 동영상 태그 및 재생 버튼 추가 -->
    <div v-for="video in videoFiles" :key="video.id">
      <video class="video" controls preload @timeupdate="updateCurrentTime" @play="handleVideoPlay">
        <source :src="video.src" type="video/mp4" />
      </video>
      <button @click="saveCurrentTime('start')">시작 시간 저장</button>
      <button @click="saveCurrentTime('end')">종료 시간 저장</button>
      <button @click="clearTimes">시간 초기화</button>
    </div>

    <div>
        <video ref="videoRef" controls></video>
        <button @click="playSegment">재생</button>
    </div>
  </div>
  <SendButton></SendButton>
</template>

<style scope>
body {
  min-height: 100vh;
  min-height: -webkit-fill-available;
}

html {
  height: -webkit-fill-available;
}

.sch-container {
  display: flex;
  height: 100vh;
  width: 100%;
  flex-direction: row;
}

.container-sidebar {
  flex: 1;
}

.container-editor2 {
  flex: 3;
}

/* .item:nth-child(1) {
  flex-grow: 1;
}

.item:nth-child(2) {
  flex-grow: 4;
} */

.video {
  width: 100%;
  height: 200px;
}
</style>
