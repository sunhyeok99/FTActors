import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useMontageStore = defineStore("montageStore", () => {
    // 몽타쥬 리스트 저장
    const montageList = ref([]);
    // 몽타쥬 리스트 불러오는 메서드
    const getMontageList = async function (page, keyword) {
      try {
        const response = await axios.get(
          `${import.meta.env.VITE_API_BASE_URL}board/list?page=${page}&keyword=${keyword}`,
        );
        montageList.value = response.data.result.montageList;
      } catch (error) {
        console.log(error);
      }
    };
    return { montageList, getMontageList };
  },
  { persist: true }
);
