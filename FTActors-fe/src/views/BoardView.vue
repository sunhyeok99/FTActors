<template>
  <div>
    <SearchBar />
  </div>
  <div>
    <BoardAll />
  </div>
  <div>
    <Pagenation />
  </div>
  </template>
  <script setup>
import BoardAll from '@/components/BoardAll.vue';
import Pagenation from '../components/Pagenation.vue';
import SearchBar from '../components/SearchBar.vue';
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
  