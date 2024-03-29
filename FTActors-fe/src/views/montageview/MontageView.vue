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
  import Pagenation from '@/components/common/Pagenation.vue';
import MontageAll from '@/components/montagepage/MontageAll.vue';
import MontageSearchBar from '@/components/montagepage/MontageSearchBar.vue';

  </script>
  <style>
  </style>
