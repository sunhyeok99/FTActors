<template>
  <div>
    <ProfileSearchBar />
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
import ProfileSearchBar from '@/components/profilepage/ProfileSearchBar.vue';

</script>

<style>

</style>