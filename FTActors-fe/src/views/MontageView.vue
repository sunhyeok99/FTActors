<template>
  <div>
    <MontageSearchBar />
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
  import MontageSearchBar from '../components/MontageSearchBar.vue';
  </script>
  <style>
  </style>
  