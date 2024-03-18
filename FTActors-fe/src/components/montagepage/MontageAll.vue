<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" v-for="(montage, index) in montages" :key="index" @click="goToMontageDetail(montage.id)">
      <div class="card" id="montage">
        <!-- <img src="../assets/ThumbNail.png" alt="사진 업로드 실패"> -->
        <iframe  src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
        title="YouTube video player" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen></iframe>
        <!-- <img :src="profile.image" class="card-img-top" alt="사진 업로드 실패"> -->
        <div class="card-body">
          <h5 class="card-title"><b>{{ montage.name }}</b></h5>
          <p class="card-text">{{ montage.title }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const montages = [
  { id: 1, name : "김지원",title: "홀씨", },
  { id: 2, name: "김지투", title: "Shopper",},
  { id: 3, name : "김지삼", title: "관객이 될게",  },
  { id: 4, name : "김지사", title: "Shh...",},
  { id: 5, name : "김지오", title: "Love wins all", },
 
];

const goToMontageDetail = (montageId) => {
  router.push({ name: 'montageDetail' , params: { id: montageId }});
};

</script>
<style>
#board {
  border: none;
}
</style>