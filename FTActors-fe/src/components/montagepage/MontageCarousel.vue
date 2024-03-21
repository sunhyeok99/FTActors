<template>

    <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <video src="@/assets/stage.mp4" muted autoplay playsinline></video>
            <div class="carousel-caption d-none d-md-block">
              <h2>톰 크루즈</h2>
              <p>The Cook</p>
            </div>
          </div>
          <div class="carousel-item">
            <video src="@/assets/stage.mp4" muted autoplay playsinline></video>
            <div class="carousel-caption d-none d-md-block">
              <h2>티모시 살라메</h2>
              <p>The Cook</p>
            </div>
          </div>
          <div class="carousel-item">
            <video src="@/assets/stage.mp4" muted autoplay playsinline></video>
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
</script>

<style scoped>

.carousel-item video {
  width: 100%; /* 비디오 너비를 캐러셀 항목의 너비에 맞춤 */
  height: 100%; /* 비디오 높이를 캐러셀 항목의 높이에 맞춤 */
  object-fit: cover; /* 비디오가 콘테이너를 꽉 채우도록 조정 */
}

.carousel-inner {
  width: 100%; /* 캐러셀 내부의 너비를 지정 */
  height: 530px; /* 캐러셀의 높이를 지정, 필요에 따라 조정 */
}
</style>