<template>
  <div class="wrapper">
    <h2>접수된 신고 총 {{reports.length}}개</h2>
    <div class="row row-cols-1 row-cols-md-2 g-4">
      <!-- 신고 카드 -->
      <div class="col-lg-3" v-for="(report, index) in reports" :key="index" id="one">        
        <img :src="report.link" alt="" id="reportImage"  :data-bs-toggle="'modal'" :data-bs-target="`#reportDetailModal${index}`">
        <h3>{{ report.reporteeId }}</h3> 
        <p>{{ report.reporterId }} - {{ report.reason }}</p> 
        <!-- 신고 상세 모달 -->
        <div class="modal fade" :id="`reportDetailModal${index}`" tabindex="-1" aria-labelledby="reportDetailModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="reportDetailModalLabel">{{report.reporterId}} 님의 {{report.reporteeId}} 신고 사유</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <img :src="report.link" alt="" id="reportImageDetail">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>

              </div>
            </div>
          </div>
        </div>
        <div class="accept-rejection">
        <p><a class="btn btn-secondary" id="accept" :href="`#details${index}`" @click="acceptReport(report.reportId)" >신고 수락</a></p> 
        <p><a class="btn btn-secondary" id="rejection" :href="`#details${index}`" @click="rejectReport(report.reportId)" >신고 거절</a></p> 
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

const acceptReport = (reportId) => {
  const body = {
    reportId : reportId
  }
  console.log(reportId)
  axios.post(`http://localhost:8080/admin/report/acceptance`, body)
    .then((response) => {
      alert(response.data.message)
      console.log(response.data)
      getReports();
    })
    .catch((error) => {
      console.error(error);
    });
};

const rejectReport = (reportId) => {
  const body = {
    reportId : reportId
  }
  console.log(reportId)
  axios.post(`http://localhost:8080/admin/report/rejection`, body)
    .then((response) => {
      alert(response.data.message)
      console.log(response.data)
      getReports();
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
#reportImageDetail{
  width: 100%;
  height: 100%;
}
</style>