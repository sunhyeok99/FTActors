<template>
  <div>
    <SearchBar />
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
import SearchBar from '../components/SearchBar.vue';
</script>

<style>

</style>