<template>
  <div v-if="selectedChat" class="mt-4">
    <h5>선택된 채팅:</h5>
    <p>이름: {{ selectedChat.name }}</p>
    <p>날짜: {{ selectedChat.day }}</p>
    <p>메시지: {{ selectedChat.message }}</p>
  </div>
  </template>
  <script setup>
    import { ref } from 'vue';
  const selectedChat = ref(null);
  
  function selectChat(chat) {
    selectedChat.value = chat;
  }
</script>
  <style></style>