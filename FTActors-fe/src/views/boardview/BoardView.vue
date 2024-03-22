<template>
  <div>
    <BoardSearchBar />
  </div>
  <div>
    <BoardAll />
  </div>
  <button @click="boardCreate" class="btn-create">공고 작성</button>

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

const boardCreate = () => {
  router.push({ name: 'boardCreate' });
};

</script>

  <style>
.btn-create {
  margin-top: 20px;
}
  </style>
  ../components/BoardSearchBar.vue../components/searchbars/BoardSearchBar.vue../components/boardpage/BoardSearchBar.vue../components/common/Pagenation.vue