<script setup>
import "bootstrap/dist/css/bootstrap.min.css";

import { ref, defineProps, defineEmits } from "vue";

// const { attrList } = defineProps({
//   attrList: Array,
// });

// const {sido} = defineProps({
//   sido : Number
// })

const isShowing = ref(false);
const selectedVideoIndex = ref(null);

const props = defineProps({
    videoList : Array
})

const emit = defineEmits(["videoSelected"]); // videoSelected 이벤트 정의

const {videoList} = props;

const selectVideo = (index) => {
    emit("videoSelected", index); // videoSelected 이벤트 발생
};


</script>

<template>
  <div class="container-sidebar">
    <div
      class="d-flex flex-column flex-shrink-0 bg-body-tertiary"
      style="width: 4.5rem; z-index: 10"
    >
      <ul class="nav nav-pills nav-flush flex-column mb-auto text-center">
        <li class="nav-item"></li>
        <li>
        </li>
      </ul>
    </div>
    <div
      class="d-flex flex-column align-items-stretch flex-shrink-0 bg-body-tertiary"
      style="width: 10rem"
    >
      <div class="list-group list-group-flush border-bottom scrollarea">
        <li v-for="(item, index) in videoList" :key="index" @click="selectVideo(index)">
            <video class="video-list" controls="controls" autoplay="autoplay" preload="auto" >
              <source :src=item.src type="video/mp4" />
            </video>
        </li>
      </div>
    </div>
    <button
      data-bs-toggle="collapse"
      role="button"
      data-bs-target="#basket"
      aria-expanded="false"
      aria-controls="basket"
      style="background-color: white; border: 2px solid #85d5e4"
    >
    </button>
    <div
      id="basket"
      class="collapse collapse-horizontal"
      style="background-color: #f8f9fa"
    >
    </div>
  </div>
</template>

<style scoped>
/* .container-sidebar {
  display: flex;
  width: fit-content;
  height: 100%;
  position: relative;
} */

.container-sidebar {
  flex: 1;
}

div#basket {
  position: absolute;
  right: 0;
  transform: translateX(100%);
  z-index: 100;
}

.dropdown-toggle {
  outline: 0;
}

.list-title {
  background-color: #060606;
}

.list-group {
  height: 100vh;
}

.video-list {
  width: 100%;
  height: 200px;
}
</style>
