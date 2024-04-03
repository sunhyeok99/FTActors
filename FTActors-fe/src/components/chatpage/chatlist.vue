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
          <!-- <a href="#" class="list-group-item list-group-item-action py-3 lh-tight"  -->
            <div class="list-group-item list-group-item-action py-3 lh-tight" 
              v-for="(chat, index) in chats" 
              :key="index" 
              @click.prevent="selectChat(chat)">
              <router-link :to="{ name: 'ChatRoomView', params: { roomId: chat.id }}">
                {{ chat.title }}
              </router-link>
              <!-- <div class="d-flex w-100 align-items-center justify-content-between">
                <strong class="mb-1">{{ chat.name }}</strong>
                <small class="text-muted">{{ chat.day }}</small>
              </div>
              <div class="col-10 mb-1 small">{{ chat.message }}</div> -->
            </div>
            <!-- </a> -->
        </div>
      </div>
      <!-- <div v-for="(chat, index) in chats" :key="index">
        <router-link :to="{ name: 'ChatDetail', params: { id: chat.id }}">
          {{ chat.name }}
        </router-link>
      </div> -->
    </div>
<!-- <ChatDetail />  -->
    
  </div>
</template>
  
<script setup>

import axios from 'axios';
import { ref, onMounted } from 'vue';
import { RouterLink, RouterView, useRouter } from 'vue-router'
import { recruitmentApi, memberApi, followApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";

const chats = ref([]);
const MemberStore = useMemberStore();
const router = useRouter();
const member = ref({});
const selectedChat = ref(null);
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
const fetchMember = async () => {
  const id = loginMember.value
      const response = await memberApi.getById(id);
      member.value = response.data.data;
  };
function selectChat(chat) {
  selectedChat.value = chat;
}

onMounted(async () => {
  try {
    // Todo : memberId 1 고정 -> 추후 변경
    let memberId = 1;
    const response = await axios.get("http://localhost:8080/chat/room/mylist", {
        params: { memberId },
      });
    chats.value = response.data.data; // 서버로부터 받은 데이터를 chats에 할당
    console.log(response.data.data)
  } catch (error) {
    console.error('Error fetching chat rooms:', error);
  }
});
onMounted(fetchMember);
</script>

<style scoped>
.list-group-item{
  flex-direction: column;
  cursor: pointer;
}

.list-group-item-action {
  color: hsla(53, 100%, 50%, 0.428);
}    

</style>
  