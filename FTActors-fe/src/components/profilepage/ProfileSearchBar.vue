<template>
    <div class="column search-bar">
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              프로필 정렬
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">오래된순</a></li> <!-- @#$prfile/list?sorting = 2-->
              <li><a class="dropdown-item" href="#">최신순</a></li> <!-- @#$prfile/list?sorting = 1-->
              <!-- <li><a class="dropdown-item" href="#">좋아요순</a></li> -->
            </ul>
          </div>
        
          <!-- @#$검색창 앞에 토클 필요 (예명 검색 or 내용 검색) -->
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
    justify-content: space-between;
    align-items: center;
}
#search{
    width: auto;
}
</style>