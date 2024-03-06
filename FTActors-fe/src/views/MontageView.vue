<template>
  <div>
    <SearchBar />
  </div>
    <div>
      <MontageAll/>
    </div>
    <div>
      <Pagenation />
    </div>
  </template>
  
  <script setup>
  import MontageAll from '@/components/MontageAll.vue';
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
  