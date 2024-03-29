<template>
<button @click="boardCreate" class="btn btn-create" type="submit">공고 작성</button>

 <BoardCreateModal />


</template>

<script setup>

import { useRouter } from 'vue-router';
import BoardCreateModal from "@/components/modals/BoardCreateModal.vue";

const router = useRouter();

const boardCreate = () => {
  router.push({ name: 'boardCreate' });
};



</script>
<style></style>
