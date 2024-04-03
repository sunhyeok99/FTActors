<template>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">{{ alarmUnReadList.length }}개의 알람</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div v-show="isAlarmUnReadListAvailable">
            <div v-for="(alarm, index) in alarmUnReadList" :key="index" class="rowthings">

              <p :class="{ 'selected': isSelected(alarm.id) }"><strong>{{ alarm.content }}</strong></p>

              <button class="btn btn-sm" @click.stop="markIndividualAsRead(alarm.id)"> <img
                  src="@/assets/icons/like-filled.png" alt="" class="alarm"></button>
            </div>
          </div>
          <div v-show="!isAlarmUnReadListAvailable">
            <p>알람이 없습니다.</p>
          </div>
        </div>

      </div>
    </div>
  </div>
  <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal">
    <img src="@/assets/icons/like-filled.png" alt="" class="alarm">
    <p class="alarmCount">{{ alarmUnReadList.length }}</p>
  </button>
</template>

<script setup>
import { onMounted, ref, nextTick } from 'vue';
import axios from 'axios';
const alarmUnReadList = ref([]);
const isAlarmUnReadListAvailable = ref(false);

const selectedAlarms = ref([]);
const loginId = 1;
const SERVER_URL = 'https://j10a602.p.ssafy.io/api';
onMounted(async () => {
  try {
    const response = await axios.get(`${SERVER_URL}/notify/list`, { params: { loginId } });
    alarmUnReadList.value = response.data.data.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
    isAlarmUnReadListAvailable.value = alarmUnReadList.value.length > 0;
    console.log("alarmUnReadList.value : ", alarmUnReadList.value);
    console.log(alarmUnReadList.value.length)
    await nextTick();
    isAlarmUnReadListAvailable.value = true;
  } catch (error) {
    console.error('Error fetching room:', error);
  }
});

const toggleAlarmSelection = (alarm) => {
  const index = selectedAlarms.value.indexOf(alarm.id);
  if (index === -1) {
    selectedAlarms.value.push(alarm.id);
  } else {
    selectedAlarms.value.splice(index, 1);
  }
};

const markIndividualAsRead = async (alarmId) => {
  try {
    // 서버에 단일 알람 읽음 상태로 업데이트 요청
    const response = await axios.post(`${SERVER_URL}/notify/read`, [alarmId]);
    console.log('Alarm marked as read:', response.data);

    // 읽음 처리된 알람을 목록에서 제거
    const index = alarmUnReadList.value.findIndex(alarm => alarm.id === alarmId);
    if (index !== -1) {
      alarmUnReadList.value.splice(index, 1);
    }
  } catch (error) {
    console.error('Error marking alarm as read:', error);
  }
};

const isSelected = (alarmId) => {
  return selectedAlarms.value.includes(alarmId);
};

const markAsRead = async () => {
  try {
    console.log("selectedAlarms : ", selectedAlarms.value);
    // 선택된 알람들의 ID를 서버에 전송하여 읽음 상태로 변경
    const response = await axios.post(`${SERVER_URL}/notify/read`, selectedAlarms.value);
    console.log('Selected alarms marked as read:', response.data);

    // 선택된 알람들을 알람 목록에서 제거
    selectedAlarms.value.forEach(selectedAlarmId => {
      const index = alarmUnReadList.value.findIndex(alarm => alarm.id === selectedAlarmId);
      if (index !== -1) {
        alarmUnReadList.value.splice(index, 1);
      }
    });
    // 선택된 알람들 초기화
    selectedAlarms.value = [];
  } catch (error) {
    console.error('Error marking alarms as read:', error);
  }
};



</script>

<style scoped>
.selected {
  background-color: hsla(53, 100%, 50%, 0.428);
  transition: background-color 0.3s ease-in-out;
  /* 부드러운 배경 색상 전환 추가 */
}

#likebtn {
  width: 30px;
  height: 30px;
}

.rowthings {
  display: flex;
  align-items: center;
  /* 항목들이 세로 중앙에 위치하도록 함 */
  padding: 15px;
  /* 내부 여백을 늘림 */
  border: 1px lightgray solid;
  margin: 15px;
  /* 마진 조정으로 간격 증가 */
  border-radius: 15px;
  transition: transform 0.3s ease-in-out;
  /* 클릭 시 약간 확대되는 효과 추가 */
  cursor: pointer;
  /* 마우스 오버 시 포인터로 변경 */
}

.rowthings:hover {
  transform: scale(1.03);
  /* 마우스 오버 시 항목 확대 */
}

/* 추가: 각 알람 항목의 크기를 약간 늘림 */
.rowthings p {
  margin: 0 10px;
  /* 텍스트와 아이콘 사이의 여백 추가 */
  font-size: 1.1rem;
  /* 텍스트 크기 증가 */
}

.alarm {
  width: 30px;
  height: 30px;
}

.alarmCount {
  color: white;
  font-weight: 600;
  background-color: orangered;
  font-size: 10px;
  border-radius: 1000px;
  margin: 5px;
}
</style>