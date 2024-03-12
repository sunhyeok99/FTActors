<template>
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
    <div class="col" @click="goToMontageDetail">
      <div class="card" id="board">
        <img src="@/assets/ThumbNail.png" alt="">
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const goToMontageDetail = () => {
  router.push({ name: 'montageDetail' });
};
</script>
<style>
#board {
  border: none;
}
</style>