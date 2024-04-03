<template>
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">알람</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <!-- 통신하여 가져온 안 읽은 알림 목록을 반복하여 표시 -->
          <div v-show="isAlarmUnReadListAvailable">
            <div v-for="(alarm, index) in alarmUnReadList" :key="index" @click="toggleAlarmSelection(alarm)">
              <!-- 여기서 알람 내용을 표시하거나 필요한 작업을 수행 -->
              <p :class="{ 'selected': isSelected(alarm.id) }">{{ alarm.content }}</p>
            </div>
          </div>
          <div v-show="!isAlarmUnReadListAvailable">
            <p>알람이 없습니다.</p>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
          <button type="button" class="btn btn-primary" @click="markAsRead">읽음</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, nextTick } from 'vue';
import axios from 'axios';

const alarmUnReadList = ref([]);
const isAlarmUnReadListAvailable = ref(false);
const selectedAlarms = ref([]);
const loginId = 1;

onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/notify/list`, { params: { loginId }});
    alarmUnReadList.value = response.data.data.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
    console.log("alarmUnReadList.value : ", alarmUnReadList.value);
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

const isSelected = (alarmId) => {
  return selectedAlarms.value.includes(alarmId);
};

const markAsRead = async () => {
  try {
    console.log("selectedAlarms : ", selectedAlarms.value);
    // 선택된 알람들의 ID를 서버에 전송하여 읽음 상태로 변경
    const response = await axios.post('http://localhost:8080/notify/read', selectedAlarms.value);
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
  background-color: yellow;
}
</style>