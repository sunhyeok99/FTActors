<template>
  <div class="container" id="app" v-cloak>
    <div>
      <h2>{{ room.title }}</h2>
    </div>
    <div class="input-group">
      <div class="input-group-prepend">
        <label class="input-group-text">내용</label>
      </div>
      <input type="text" class="form-control" v-model="message" @keypress.enter="sendMessage">
      <div class="input-group-append">
        <button class="btn btn-primary" type="button" @click="sendMessage">보내기</button>
      </div>
    </div>
    <ul class="list-group">
      <li class="list-group-item" v-for="message in messages" :key="message.id">
        {{ message.sender }} - {{ message.message }}
      </li>
    </ul>
    <div></div>
  </div>
</template>

<script>
import axios from 'axios';
import Stomp from 'stompjs';

export default {
  data() {
    return {
      roomId: '',
      room: {},
      sender: '1',
      message: '',
      messages: [],
      stompClient: null
    };
  },
  created() {
    this.roomId = this.$route.params.roomId;
    // 이거 로그인 한 사용자 id 할당하자
    // this.roomId = this.$route.params.roomId;
    this.findRoom();
  },
  methods: {
    findRoom() {
      axios.get('/chat/room/id?roomId=' + this.roomId)
        .then(response => {
          this.room = response.data;
        })
        .catch(error => {
          console.error('Error fetching room:', error);
        });
    },
    sendMessage() {
      this.stompClient.send("/pub/chat.talk." + this.roomId, {}, JSON.stringify({
        roomId: this.roomId,
        sender: this.sender,
        message: this.message,
        type: 'TALK',
      }));
      this.message = '';
    },
    recvMessage(recv) {
      this.messages.unshift({
        "type": recv.type,
        "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender,
        "message": recv.message
      });
    },
    connect() {
      const socket = new WebSocket('ws://localhost:8080/ws-stomp');
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect({}, frame => {
        console.log('Connected: ' + frame);
        this.stompClient.subscribe('/exchange/chat.exchange/*.room.' + this.roomId, message => {
          const recv = JSON.parse(message.body);
          this.recvMessage(recv);
        });
        this.stompClient.send("/pub/chat.talk." + this.roomId, {}, JSON.stringify({
          roomId: this.roomId,
          sender: this.sender,
          message: this.message,
          type: 'ENTER'
        }));
      }, error => {
        console.error('STOMP connection error:', error);
        // 연결이 닫히면 재연결 시도
        setTimeout(() => {
          this.connect();
        }, 10000); // 10초 후 재시도
      });
    }
  },
  mounted() {
    this.connect();
  }
};
</script>

<style>
[v-cloak] {
  display: none;
}
</style>
