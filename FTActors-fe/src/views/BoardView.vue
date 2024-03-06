<template>
  <div>
    <Board />
  </div>
  <div>
    <Pagenation />
  </div>
  </template>
  <script setup>
import Board from '@/components/Board.vue';
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
  