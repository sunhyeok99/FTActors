import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useProfileStore = defineStore(
  "profileStore",
  () => {
    // 프로필 리스트 저장
    const profileList = ref([]);
    // 프로필 리스트 불러오기
    const getProfileList = async function () {
      axios
        .get(`${import.meta.env.VITE_API_BASE_URL}category/getProfileList`)
        .then((res) => {
         profileList.value = res.data.result;
        });
    };

    return { profileList, getProfileList };
  },
  { persist: true }
   // Pinia 스토어의 상태를 브라우저의 로컬 스토리지에 저장하여 페이지 리로드나 재방문 시에도 상태를 유지하게 합니다.
);
