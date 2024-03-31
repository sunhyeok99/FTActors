<template>
  <div class="wrapper">
    <div class="row row-cols-1 row-cols-md-2 g-4">
      <div class="col-lg-3" v-for="(black, index) in blacklist" :key="index" id="one">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg"
          role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false">
          <rect width="100%" height="100%" fill="#777"></rect>
          <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
        </svg>
        <h2>{{ black.name }}</h2> 
        <p>{{ black.email }}</p> 
        <p><a class="btn btn-secondary" :href="`#details${index}`">블랙리스트 해제</a></p> 
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
const blacklist = ref([]);
const getReports = () => {
  axios.get(`http://localhost:8080/admin/black-list`)
    .then((response) => {
      blacklist.value = response.data.data
      console.log(response.data.data)
    })
    .catch((error) => {
      console.error(error);
    });
};
onMounted(() => {
  getReports();
});


</script>
<style>

#one{
  display: flex;
  flex-direction: column;
  align-items: center;
}

</style>