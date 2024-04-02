<template>

  <div>
    <BoardSearchBar />
  </div>
  <div class="spacer"></div>
  <div>
    <BoardAll />
  </div>

  <div>
    <Pagenation />
  </div>
  </template>
  <script setup>
import BoardAll from '@/components/boardpage/BoardAll.vue';
import BoardSearchBar from '@/components/boardpage/BoardSearchBar.vue';
import Pagenation from '@/components/common/Pagenation.vue';
import { useRouter } from "vue-router";

const router = useRouter();

</script>

  <style>
.btn-create {
  margin-top: 20px;
}
.spacer{
  height: 1rem;
}
  </style>
