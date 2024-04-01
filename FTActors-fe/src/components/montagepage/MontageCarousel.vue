<template>

    <div id="carouselExampleAutoplaying" class="carousel slide w-75 m-auto" data-bs-ride="carousel">
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

      </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router'

const router = useRouter();


const goToMontageDetail = () => {
  router.push({ name: 'montageDetail' });
};

</script>

<style scoped>

.carousel-item video {
  width: 100%; /* 비디오 너비를 캐러셀 항목의 너비에 맞춤 */
  height: 100%; /* 비디오 높이를 캐러셀 항목의 높이에 맞춤 */
  object-fit: cover; /* 비디오가 콘테이너를 꽉 채우도록 조정 */
  object-position: center; /* 비디오의 중심을 캐러셀의 중심에 맞춤 */
}

.carousel-inner {
  width: 100%; /* 캐러셀 내부의 너비를 지정 */
  height: 100%; /* 캐러셀의 높이를 지정, 필요에 따라 조정 */
}
</style>