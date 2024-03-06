<template>
    <div class="profile">
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
  