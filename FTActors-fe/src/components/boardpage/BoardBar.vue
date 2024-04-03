<template>
  <div class="column search-bar">
    <div class="dropdown">
      <button class="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
        공고 배열
      </button>
      <ul class="dropdown-menu">
        <li><a class="dropdown-item" href="#">기간순</a></li>
        <li><a class="dropdown-item" href="#">최신순</a></li>
        <li><a class="dropdown-item" href="#">추천순</a></li>
      </ul>
    </div>
    <BoardCreateModal />
  </div>
</template>

<script setup>
import BoardCreateModal from '@/components/modals/BoardCreateModal.vue';
</script>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.dropdown-menu {
  z-index: 10;
}

#search {
  width: auto;
}

</style>