<script setup>
import { ref, watch, onMounted, onBeforeUnmount, provide } from "vue";
import VideoSideBar from "./VideoSideBar.vue";
// import Editor from "./Editor.vue"
import Editor from "./Editor2.vue"

const videoFiles = ref([
  { src: '/src/assets/montage/콘트라베이스.mp4' },
  { src: '/src/assets/montage/핸드크림.mp4' },
  { src: '/src/assets/montage/담요.mp4' }
]);

const videos = ref({});

const selectedVideoIndex = ref(null);

const handleVideoSelected = (index) => {
  selectedVideoIndex.value = index;
  console.log("selected video index from Main !! ", selectedVideoIndex.value);
};

// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

const currentTime = ref(0);

const updateCurrentTime = () => {
  if (videoElement.value) {
    currentTime.value = videoElement.value.currentTime;
  }
};

const saveCurrentTime = () => {
  console.log("Current Time:", currentTime.value);
};

// 비디오 요소 참조
const videoElement = ref(null);

onMounted(() => {
  videoElement.value.addEventListener('timeupdate', updateCurrentTime);
});

onBeforeUnmount(() => {
  videoElement.value.removeEventListener('timeupdate', updateCurrentTime);
});

const handleVideoPlay = () => {
  videoElement.value = document.querySelector('.video');
};

// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


</script>

<template>
  <div class="sch-container">
    <Role></Role>
    <!-- <VideoSideBar :videoList="videoFiles" class="item" @videoSelected="handleVideoSelected" />
    <Editor2 :videoList="videoFiles" class="item" :selectedVideoIndex="selectedVideoIndex" /> -->

    <div v-for="video in videoFiles" :key="video.id">
      <video class="video" controls preload @timeupdate="updateCurrentTime" @play="handleVideoPlay">
        <source :src="video.src" type="video/mp4" />
      </video>
      <button @click="saveCurrentTime">재생 시간 저장</button>
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
</style>
