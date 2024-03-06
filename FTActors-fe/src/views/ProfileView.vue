<template>
  <div>
    <ProfileList />
  </div>
</template>

<script setup>
import ProfileList from '@/components/ProfileList.vue';
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