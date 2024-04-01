<script setup>
import "bootstrap/dist/css/bootstrap.min.css";

import { ref, defineProps } from "vue";

// const { attrList } = defineProps({
//   attrList: Array,
// });

// const {sido} = defineProps({
//   sido : Number
// })

const isShowing = ref(false);

const props = defineProps({
    videoList : Array
})

const {videoList} = props;


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
          <ScheduleStepButton name="일정 설정"></ScheduleStepButton>
          <ScheduleStepButton name="장소 설정"></ScheduleStepButton>
        </li>
      </ul>
    </div>
    <div
      class="d-flex flex-column align-items-stretch flex-shrink-0 bg-body-tertiary"
      style="width: 20rem"
    >
      <div
        class="list-title d-flex align-items-center flex-shrink-0 p-3 link-body-emphasis text-decoration-none border-bottom mb-2"
      >
        <svg class="bi pe-none me-2" width="30" height="24">
          <use xlink:href="#bootstrap" />
        </svg>
        <span class="mx-auto fs-5 fw-semibold" style="color: white"
          >영상 목록</span
        >
      </div>
      <h3 class="mx-auto m-4">여행 일정을 정해주세요!</h3>
      <div class="list-group list-group-flush border-bottom scrollarea">
        <li v-for="(item, index) in videoList" :key="index">
            <video :src="item.src"></video>
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
.container-sidebar {
  display: flex;
  width: fit-content;
  height: 100%;
  position: relative;
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

.btn-toggle {
  padding: 0.25rem 0.5rem;
  font-weight: 600;
  color: var(--bs-emphasis-color);
  background-color: transparent;
}
.btn-toggle:hover,
.btn-toggle:focus {
  color: rgba(var(--bs-emphasis-color-rgb), 0.85);
  background-color: var(--bs-tertiary-bg);
}

.btn-toggle::before {
  width: 1.25em;
  line-height: 0;
  content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%280,0,0,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
  transition: transform 0.35s ease;
  transform-origin: 0.5em 50%;
}

[data-bs-theme="dark"] .btn-toggle::before {
  content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%28255,255,255,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
}

.btn-toggle[aria-expanded="true"] {
  color: rgba(var(--bs-emphasis-color-rgb), 0.85);
}
.btn-toggle[aria-expanded="true"]::before {
  transform: rotate(90deg);
}

.btn-toggle-nav a {
  padding: 0.1875rem 0.5rem;
  margin-top: 0.125rem;
  margin-left: 1.25rem;
}
.btn-toggle-nav a:hover,
.btn-toggle-nav a:focus {
  background-color: var(--bs-tertiary-bg);
}

.list-title {
  background-color: #dfcdeb;
}

.list-group {
  height: 100vh;
}
</style>
