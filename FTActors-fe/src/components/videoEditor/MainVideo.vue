<script setup>
import { ref, watch, onMounted, provide } from "vue";
import VideoSideBar from "./VideoSideBar.vue";
import Editor from "./Editor.vue"

import { videoApi } from '@/util/axios';
import { useRoute, useRouter } from "vue-router";


//import { storeToRefs } from "pinia";
//import { useAttrStore } from "@/stores/schedule";

// const store = useAttrStore();
// //const { setSidoCode } = storeToRefs(store);

// const { dataObj } = history.state; // 이렇게 받는다.

// if (!!dataObj) {
//   console.log(dataObj);
//   store.setSidoCode(dataObj.sido);
// }

const videoFiles = ref([
  { src: '@/assets/montage/콘트라베이스.mp4' },
  { src: '@/assets/montage/핸드크림.mp4' },
  { src: '@/assets/montage/담요.mp4' }
]);

import { recruitmentApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";
  
// const MemberStore = useMemberStore();
// const loginMember = ref(null);
// loginMember.value = MemberStore.memberInfo;
// const adminId = 11;

// const router = useRouter();
// const boards = ref([]);

// // getList 함수 정의: 백엔드로부터 공고 리스트를 받아오는 함수
// const getList = async (memberId) => {
//   try {
//     await recruitmentApi.getList(memberId).then((res) => {
//       boards.value = res.data.data;
//     })   
//   } catch (error) {
//     console.error('Error fetching recruitment list:', error);
//   }
// };


const recruitmentId = ref(2);
//const videoFiles = ref([])
onMounted(
  async ()=>{
    await videoApi.getAllPerformVideo(recruitmentId.value)
    .then((res) => {
      console.log(res)
      console.log(res.data.data);
      videoList.value = res.data.data;
    })  
  }
)



</script>

<template>
  <div class="sch-container">
    <Role></Role>
    <VideoSideBar :videoList="videoFiles" class="item"></VideoSideBar>
    <Editor class="item"></Editor>
  </div>
  <SendButton></SendButton>
</template>

<style scope>
body {
  min-height: 100vh;
  min-height: -webkit-fill-available;
}

html {
  height: -webkit-fill-available;
}

.sch-container {
  display: flex;
  height: 100vh;
  width: 100%;
  flex-direction: row;
}

.item:nth-child(1) {
  flex-grow: 1;
}

.item:nth-child(2) {
  flex-grow: 4;
}
</style>
