<template>
  <div class="container" id="app" v-cloak>
    <div>
      <h2>{{ room.title }}</h2>
    </div>
    <ul class="list-group chatbox">
      <li class="list-group-item" v-for="message in messages" :key="message.id" :class="{ 'text-end': message.sender == loginMember.value, 'text-start': message.sender !== loginMember.value }">
        {{ message.sender }} - {{ message.message }}
      </li>
    </ul>
    <div class="input-group">
      <div class="input-group-prepend">
        <label class="input-group-text">내용</label>
      </div>
      <input type="text" class="form-control" v-model="message" @keypress.enter="sendMessage">
      <div class="input-group-append">
        <button class="btn btn-dark" type="button" @click="sendMessage">보내기</button>
      </div>
    </div>

  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import Stomp from 'stompjs';
import { useRoute } from 'vue-router';
import { useMemberStore } from "@/stores/member-store.js";

const route = useRoute();
const roomId = ref(route.params.roomId);
const room = ref({});
const sender = ref('12'); // 여기서 '12'는 예시입니다. 실제 애플리케이션에서는 동적으로 설정해야 합니다.
const message = ref('');
const messages = ref([]);
const stompClient = ref(null);

const MemberStore = useMemberStore();
const loginMember = ref(null)
loginMember.value = MemberStore.memberInfo;
console.log(loginMember.value);

const findRoom = async () => {
  try {
    const response = await axios.get('/chat/room/id?roomId=' + roomId.value);
    room.value = response.data;
  } catch (error) {
    console.error('Error fetching room:', error);
  }
};

const sendMessage = () => {
  if (stompClient.value) {
    stompClient.value.send("/pub/chat.talk." + roomId.value, {}, JSON.stringify({
      roomId: roomId.value,
      sender: sender.value,
      message: message.value,
      type: 'TALK',
    }));
    message.value = '';
  }
};

const recvMessage = (recv) => {
  messages.value.unshift({
    "type": recv.type,
    "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender,
    "message": recv.message
  });
};

const connect = () => {
  const socket = new WebSocket('ws://localhost:8080/ws-stomp');
  stompClient.value = Stomp.over(socket);

  stompClient.value.connect({}, frame => {
    console.log('Connected: ' + frame);
    stompClient.value.subscribe('/exchange/chat.exchange/*.room.' + roomId.value, message => {
      const recv = JSON.parse(message.body);
      recvMessage(recv);
    });
    stompClient.value.send("/pub/chat.talk." + roomId.value, {}, JSON.stringify({
      roomId: roomId.value,
      sender: sender.value,
      message: message.value,
      type: 'ENTER'
    }));
  }, error => {
    console.error('STOMP connection error:', error);
    setTimeout(connect, 10000); // 10초 후 재시도
  });
};

onMounted(() => {
  connect();
  findRoom();
});
</script>


<style>

.text-end {
  text-align: right;
}

.text-start {
  text-align: left;
}


[v-cloak] {
  display: none;
}


.chatbox {
  border: 1px gray solid;
  border-radius: 10px;
  max-height: 400px;
  overflow-y: auto; /* 채팅박스 스크롤 가능하게 */
}

</style>