<template>
<button @click="boardCreate" class="btn btn-create" type="submit">공고 작성</button>

 <BoardCreateModal />


</template>

<script setup>
import { ref } from "vue";
import { useRouter } from 'vue-router';
import BoardCreateModal from "@/components/modals/BoardCreateModal.vue";
import { useMemberStore } from "@/stores/member-store.js";
  
const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;

const router = useRouter();

const boardCreate = () => {
  if(loginMember.value == ""){
    alert(" 로그인이 필요합니다")
    router.push({ name : 'board'});
  }
  else{
    router.push({ name: 'boardCreate' });
  }
};



</script>
<style></style>
