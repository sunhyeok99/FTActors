import { ref, onMounted, watch } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

export const useUserStore = defineStore(
  'userStore',
  () => {
    const loginUserInfo = ref({});
    const userStore = useUserStore();

    const getLoginUserInfo = async function () {
      const body = {
        sign: 'getMyPage',
      };

      axios
        .post(`${import.meta.env.VITE_API_BASE_URL}user`, body, {
          headers: {
            'Content-Type': 'application/json',
          },
        })
        .then((res) => {
          console.log(res.data.result);
          loginUserInfo.value = res.data.result;
          userStore.$patch({ loginUserInfo: loginUserInfo.value });
        })
        .then(() => {
          login();
        });
    };

    return { loginUserInfo, getLoginUserInfo
    };
  },

);
