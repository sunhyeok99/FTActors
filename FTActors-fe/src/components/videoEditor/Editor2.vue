<template>
  <div class="container-editor2">
    <h2>선택된 비디오</h2>
    <div v-for="(item, index) in videoList" :key="index">
      <!-- <video v-show="index === selectedVideoIndex" controls preload> -->
      <video controls preload>
        <source :src=item.src type="video/mp4" />
      </video>
    </div>
  </div>
</template>

<script setup>
import { defineProps, ref, watch } from "vue";

const props = defineProps({
  videoList: Array, // props 정의
  selectedVideoIndex: {
    type: Number,
    default: null // 초기값 설정
  }
});

const {videoList} = props;

const selectedVideo = ref(null);
</script>

<style scoped>
.container-editor2 {
  flex: 3;
}
</style>