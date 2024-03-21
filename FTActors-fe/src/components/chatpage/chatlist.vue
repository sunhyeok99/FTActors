<template>
    <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions"
      aria-labelledby="offcanvasWithBothOptionsLabel">
      <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel"><b>채팅 목록</b></h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <div class="d-flex flex-column align-items-stretch flex-shrink-0 bg-white" style="width: 380px;">
          <div class="list-group list-group-flush border-bottom scrollarea">
            <a href="#" class="list-group-item list-group-item-action py-3 lh-tight" 
               v-for="(chat, index) in chats" 
               :key="index" 
               @click.prevent="selectChat(chat)">
              <div class="d-flex w-100 align-items-center justify-content-between">
                <strong class="mb-1">{{ chat.name }}</strong>
                <small class="text-muted">{{ chat.day }}</small>
              </div>
              <div class="col-10 mb-1 small">{{ chat.message }}</div>
            </a>
          </div>
        </div>
        <div v-for="(chat, index) in chats" :key="index">
            <router-link :to="{ name: 'ChatDetail', params: { id: chat.id }}">
              {{ chat.name }}
            </router-link>
          </div>
        </div>
<ChatDetail /> 
     
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { RouterLink, RouterView, useRouter } from 'vue-router'
  import ChatDetail from '@/components/chatpage/chatdetail.vue';
  
  const chats = [
    { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
    { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
    { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
    { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
    { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
  ];
  
  const selectedChat = ref(null);
  
  function selectChat(chat) {
    selectedChat.value = chat;
  }
  </script>
  
  <style>
  .list-group-item{
    flex-direction: column;
    cursor: pointer;
  }

  </style>
  