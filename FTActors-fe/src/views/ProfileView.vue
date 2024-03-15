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
import ProfileListAll from '@/components/ProfileListAll.vue';
import Pagenation from '../components/Pagenation.vue';
import ProfileSearchBar from '../components/ProfileSearchBar.vue';
</script>

<style>

</style>../components/BoardSearchBar.vue