<template>
  <div class="title"><h3><b>몽타쥬</b></h3>
    <button type="button" class="btn pageright" @click="goToMontagePage">▶️더보기</button>
  </div>
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col">
        <div class="card" id="board">
          <iframe src="https://www.youtube.com/embed/-rSVUNy8Wbs?si=i5h_YPJFjXe3KKch" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

        </div>
      </div>
      <div class="col">
        <div class="card" id="board">
          <iframe src="https://www.youtube.com/embed/IBAWkmKucb4?si=_tx_IHIIxrYhQTKq" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

        </div>
      </div>
      <div class="col">
        <div class="card" id="board">
          <iframe  src="https://www.youtube.com/embed/biLbb6PsC6s?si=B5KrNzkhq4wQAbW2" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>


        </div>
      </div>
      <div class="col">
        <div class="card" id="board">
          <iframe  src="https://www.youtube.com/embed/0c3i7aQmseY?si=hmwbwjBxZBQa32KH" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

        </div>
      </div>

    </div>
  </template>
  
  <script setup>
  import { useRouter } from 'vue-router'
const router = useRouter();
const goToMontagePage=() => {
  router.push({ name: 'montage' });
};
  </script>
  <style>
#board {
    border: none;
}
.title {
  display: flex;
}
.pageright {
  display: flex;
  margin-left: auto;
}
</style>