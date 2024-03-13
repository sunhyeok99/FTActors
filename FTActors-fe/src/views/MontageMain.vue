<template>
    <div class="montagepage">
      <div class="montagethumbnail">
        <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item active">
                <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
                title="YouTube video player" frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen></iframe>
              </div>
              <div class="carousel-item">
                <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
                title="YouTube video player" frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen></iframe>
              </div>
              <div class="carousel-item">
                <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
                title="YouTube video player" frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen></iframe>
              </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
              <span class="carousel-control-next-icon" aria-hidden="true"></span>
              <span class="visually-hidden">Next</span>
            </button>
          </div>

      </div>

    </div>
  </template>
  <script setup>
  import { useRouter, useRoute } from 'vue-router'
  
  const router = useRouter();
  const route = useRoute();
  
  </script>
  <style>
  .montagepage {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 2rem;
  }
  
  .montagethumbnail {
    display: flex;
    align-items: center;
    margin-bottom: 4rem;
  }
  
  .montagethumbnail img {
    width: 25px;
    height: 25px;
    margin: 2rem;
  }
  
  .montagedetail {
    width: 30rem;
  }
  
  #previous {
    transform: rotate(180deg);
    color-interpolation-filters : white;
    cursor: pointer;
  }
  #next {
    cursor: pointer;
  }
  
  </style>