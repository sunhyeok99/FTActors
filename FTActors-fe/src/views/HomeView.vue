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
import Notification from '../components/homepage/Notification.vue';
import ProfileList from '../components/homepage/ProfileList.vue';
import Board from '../components/homepage/Board.vue';
import Montage from '../components/homepage/Montage.vue';
import { useMemberStore, useJwtStore } from "@/stores/member-store";
</script>

<style></style>