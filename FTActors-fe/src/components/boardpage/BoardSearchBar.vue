<template>
    <div class="column search-bar">
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              공고 배열
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">기간순</a></li>
              <li><a class="dropdown-item" href="#">최신순</a></li>
              <li><a class="dropdown-item" href="#">추천순</a></li>
            </ul>
       
        </div>
        <form class="d-flex" role="search">
            <input class="form-control me-2" id="search" type="search" placeholder="키워드를 입력해 주세요" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">검색</button>
          </form>
        </div>
</template>

<script setup>
</script>

<style>
.search-bar{
    display: flex;
    align-items: center;
    justify-content: space-between;
}

#search{
    width: auto;
}
</style>