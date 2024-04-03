
<template>
  <div class="wrapper">
  <div class="row row-cols-1 row-cols-md-4 g-4">
    <div class="col">
      <div class="card h-100">
        <img src="@/assets/icons/DefaultProfile.png" class="card-img-top" alt="...">
        <div class="card-body">
          <button type="button" class="btn btn-danger" @click="goToEdit">지원영상 확인하기</button>
        </div>
      </div>
    </div>    
  </div>
</div>
</template>

<script setup>
</script>

<style>
.wrapper {
  padding-top: 2rem;
  padding-bottom: 2rem;
}
.col {
  padding: 1rem;
}
</style>