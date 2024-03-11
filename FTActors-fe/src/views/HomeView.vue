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
import Notification from '../components/Notification.vue';
import ProfileList from '../components/ProfileList.vue';
import Board from '../components/Board.vue';
import Montage from '../components/Montage.vue';
import Pagenation from '../components/Pagenation.vue';
</script>

<style></style>
