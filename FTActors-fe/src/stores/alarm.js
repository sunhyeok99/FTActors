import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

export const useAlarmStore = defineStore(
  'alarmStore',
  () => {
    // 안 읽은 알림 개수
    const alarmUnReadTotal = ref();
    // 안 읽은 알림 목록
    const alarmUnReadList = ref([]);
    // 안 읽은 알림 목록 불러오기
    const getUnReadAlarmList = function () {
      axios
        .get(`${import.meta.env.VITE_API_BASE_URL}alarms/getUncheckAlarmList`)
        .then((res) => {
          const sortedAlarmList = res.data.result.sort(
            (a, b) => new Date(b.createdDate) - new Date(a.createdDate),
          );
          alarmUnReadTotal.value = sortedAlarmList.length;
          alarmUnReadList.value = sortedAlarmList;
        });
    };
    // 새로운 알람 항목을 받아 현재 목록에 추가하기
    const updateAlarm = async (newValue) => {
      alarmUnReadTotal.value = alarmUnReadList.value.unshift(newValue);
    };
    return {
      alarmUnReadTotal,
      alarmUnReadList,
      getUnReadAlarmList,
      updateAlarm,
    };
  },
  { persist: true },
);