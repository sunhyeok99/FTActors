<template>
  <div>
    <ProfileBar />
  </div>
  <div>
    <ProfileListAll />
  </div>
  <div>
    <Pagenation />
  </div>
</template>

<script setup>
import Pagenation from '@/components/common/Pagenation.vue';
import ProfileListAll from '@/components/profilepage/ProfileListAll.vue';
import ProfileBar from '@/components/profilepage/ProfileBar.vue';

</script>

<style>

</style>