import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

export const useBoardStore = defineStore('boardStore', () => {
  // 공고 리스트 저장
  const boardList = ref([]);
  // 공고 리스트 불러오는 메서드
  const getBoardList = async function () {
    try {
      const response = await axios.get(
        `${import.meta.env.VITE_API_BASE_URL}/api/recruitment/list?memberId={value}`,
        
      );

      if (response.data.code === 1000) {
        boardList.value = response.data.result;
      }
    } catch (error) {
      console.error(error);
    }
  };

  return { boardList, getBoardList };
});