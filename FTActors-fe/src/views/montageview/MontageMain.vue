<template>
  <div class="montagethumbnail">
    <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
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

  </div>

  <div data-bs-theme="dark">
    <button type="button" class="btn-close" aria-label="Close" data-bs-dismiss="modal" id="whitebtn"></button>

  </div>

</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const goToPreviousMontage = () => {
  router.push({ name: 'montageDetail' });
};
const goToNextMontage = () => {
  router.push({ name: 'montageDetail' });
};
</script>

<style scoped>
.modal-body {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
}

.modal-content {
  background-color: black;

}

.modal-footer {
  border-top: 0;
}
</style>