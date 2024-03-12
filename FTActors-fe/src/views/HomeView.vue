<template>
  <div>
    <Notification />
  </div>
  <hr>
  <div>
    <Board />
  </div>
  <hr>
    <div>
      <Montage />
    </div>
  <hr>
  <div>
    <ProfileList />
  </div>



</template>

<script setup>
import BigMontage from '../components/BigMontage.vue';
import Notification from '../components/Notification.vue';
import ProfileList from '../components/ProfileList.vue';
import Board from '../components/Board.vue';
import Montage from '../components/Montage.vue';
</script>

<style></style>
