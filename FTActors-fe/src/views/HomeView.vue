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
  <div>
    <Pagenation />
  </div>
</template>

<script setup>
import Notification from '../components/Notification.vue';
import ProfileList from '../components/ProfileList.vue';
import Board from '../components/Board.vue';
import Montage from '../components/Montage.vue';
import Pagenation from '../components/Pagenation.vue';
</script>

<style>
@media (min-width: 1024px) {
  .profile {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
