<template>
    <div class="profile">
    </div>
    <div>
      <Montage/>
    </div>
    <div>
      <Pagenation />
    </div>
  </template>
  
  <script setup>
  import Montage from '@/components/Montage.vue';
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
  