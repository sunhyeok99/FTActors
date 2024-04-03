<template>
    <div class="column search-bar">
        <div class="dropdown">
            <button class="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              프로필 배열
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">기간순</a></li>
              <li><a class="dropdown-item" href="#">최신순</a></li>
              <li><a class="dropdown-item" href="#">좋아요순</a></li>
            </ul>
          </div>
        <form class="d-flex" role="search">
            <input class="form-control me-2" id="search" type="search" placeholder="키워드를 입력해 주세요" aria-label="Search">
            <button class="btn btn-dark" type="submit">검색</button>
          </form>
    </div>
</template>

<script setup>
</script>

<style>
.search-bar{
    display: flex;
    justify-content: space-between;
    align-items: center;
}
#search{
    width: auto;
}
</style>