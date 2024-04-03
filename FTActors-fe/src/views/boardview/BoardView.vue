<template>

  <div>
    <BoardBar />
  </div>
  <div class="spacer"></div>
  <div>
    <BoardAll />
  </div>

  </template>
  <script setup>
import BoardAll from '@/components/boardpage/BoardAll.vue';
import BoardBar from '@/components/boardpage/BoardBar.vue';
import Pagenation from '@/components/common/Pagenation.vue';
import { useRouter } from "vue-router";

const router = useRouter();

</script>

  <style scoped>
.btn-create {
  margin-top: 20px;
}
.spacer{
  height: 1rem;
}
  </style>
