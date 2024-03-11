<template>
  <h1>상세 몽타쥬 페이지</h1>
  <div class="montagepage">
    <img src="@/assets/Next.png" alt="" id="previous">
  <iframe width="560" height="315" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
<img src="@/assets/Next.png" alt="">
</div>  
</template>
  
  <style>
.montagepage{
  display: flex;
  align-items: center;
}
.montagepage img {
  width: 25px;
  height: 25px;
}
#previous {
  transform: rotate(180deg);
}
  </style>