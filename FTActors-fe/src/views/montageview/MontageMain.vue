<template>
  <div class="montagemainpage">
    <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
            title="YouTube video player" frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen></iframe>
          <div class="carousel-caption d-none d-md-block">
            <h2>티모시 살라sdfsdf메</h2>
            <p>The Cook</p>
          </div>
        </div>

        <div class="carousel-item">
          <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
            title="YouTube video player" frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen></iframe>
          <div class="carousel-caption d-none d-md-block">
            <h2>티모시 살라메</h2>
            <p>The Cook</p>
          </div>
        </div>
        <div class="carousel-item">
          <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
            title="YouTube video player" frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen></iframe>
          <div class="carousel-caption d-none d-md-block">
            <h2>티모시 살라메</h2>
            <p>The Cook</p>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying"
        data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying"
        data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button class="like-btn" :class="{ liked: isLiked }" @click.stop="toggleLike()">
            <img v-if="isLiked" src="@/assets/icons/like-filled.png" alt="Liked">
            <img v-else src="@/assets/icons/like-outline.png" alt="Like">
          </button>
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
            댓글 5개
          </button>
        </h2>
        <div class="input-group mb-3">
          <input type="text" class="form-control" placeholder="댓글 쓰기" aria-label="Recipient's username" aria-describedby="button-addon2">
          <button class="btn btn-outline-secondary" type="button" id="button-addon2">작성</button>
        </div>
        <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
          <div class="accordion-body">
            <div class="list-group list-group-flush border-bottom scrollarea">
              <a href="#" class="list-group-item list-group-item-action py-3 lh-tight" 
                 v-for="(chat, index) in chats" 
                 :key="index" 
                 @click.prevent="selectChat(chat)">
                <div class="d-flex w-100 align-items-center justify-content-between">
                  <strong class="mb-1">{{ chat.name }}</strong>
                  <div class="col-10 mb-1 small">{{ chat.message }}</div>
                </div>
                <div v-if="selectedChat" class="mt-4">
                  <p>ㄴ 대댓글입니다. 대댓대댓대댓걸</p>
                  <p>ㄴ 출처를 공개하고 유튜브 사용자들의 편의성을 고려해야 하는 링크(URL) 표시는 글이 끝나는 그 아래에 클립 아이콘과 함께 푸른 톤을
                    사용하여 콘텐츠 영역과 구분이 될 수 있게 디자인하였습니다.</p>
                </div>
              </a>
            </div>
          </div>
          <!-- 선택된 채팅 표시 -->
         
          </div>
        </div>
      </div>
    </div>



  <div data-bs-theme="dark">
    <button type="button" class="btn-close" aria-label="Close" data-bs-dismiss="modal" id="whitebtn"></button>

  </div>

</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router'
const isLiked = ref(false)
const router = useRouter();
const goToPreviousMontage = () => {
  router.push({ name: 'montageDetail' });
};
const goToNextMontage = () => {
  router.push({ name: 'montageDetail' });
};
const toggleLike = () => {
  isLiked.value = !isLiked.value;
};

const chats = [
  { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
  { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
  { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
  { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
  { name: '배사람', day: '토', message: '잘 부탁드립니다.' },
  // 여기에 다른 채팅 객체를 추가할 수 있습니다.
];

const selectedChat = ref(false);

function selectChat(chat) {
  selectedChat.value = !selectedChat.value;
}
</script>

<style scoped>
.montagemainpage {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.accordion {
  display: flex;
}

.accordion-header {
  display: flex;
  flex-direction: row;
}
.accordion-button {
  width: max-content;
}
.like-btn {
  background-color: white;
  border: none;
}
.accordion-item {
  justify-content: space-between;
  width: 80%;
}

.accordion-header button img {
  width: 30px;
  height: 30px;
}

.accordion-button {
  justify-content: right;
}
</style>