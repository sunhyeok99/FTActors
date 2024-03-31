<template>
  <div class="wrapper">
    <h2>신고 총 {{reports.length}}개</h2>
    <div class="row row-cols-1 row-cols-md-2 g-4">
      <div class="col-lg-3" v-for="(report, index) in reports" :key="index" id="one">
        <img :src="report.link" alt="" id="reportImage">
        <h3>{{ report.reporteeId }}</h3> 
        <p>{{ report.reporterId }} - {{ report.reason }}</p> 
        <div class="accept-rejection">
        <p><a class="btn btn-secondary" id="accept" :href="`#details${index}`" @click="acceptReport(report.reporteeId)">신고 수락</a></p> 
        <p><a class="btn btn-secondary" id="rejection" :href="`#details${index}`">신고 거절</a></p> 
      </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const reports = ref([]);
const getReports = () => {
  axios.get(`http://localhost:8080/admin/report/list`)
    .then((response) => {
      reports.value = response.data.data
      console.log(response.data.data)
    })
    .catch((error) => {
      console.error(error);
    });
};
onMounted(() => {
  getReports();
});

const acceptReport = (reporteeId) => {
  const body = {
    reportId : reporteeId
  }
  console.log(reporteeId)
  axios.post(`http://localhost:8080/admin/report/acceptance`, body)
    .then((response) => {
      alert(response)
      console.log('###############',reporteeId)
    })
    .catch((error) => {
      console.error(error);
    });
};



</script>
<style scoped>
h2{
  padding: 15px;
}
.accept-rejection {
  display: flex;
}
#accept {
margin: 5px;
}

#rejection {
  margin: 5px;
}

#one{
  display: flex;
  flex-direction: column;
  align-items: center;
}

#reportImage{
  width: 250px;
}
</style>