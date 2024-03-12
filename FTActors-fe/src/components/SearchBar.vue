<template>
    <div class="column search-bar">
        <select class="g-2 form-select" aria-label="Small select example">
            <option selected>태그</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
        </select>
        <div class="g-2 input-group mb-3">
            <input type="text" class="form-control" placeholder="검색" aria-label="Username"
                aria-describedby="basic-addon1">
        </div>
    </div>
</template>

<script setup>
</script>

<style>
.search-bar{
    display: flex;
    align-items: baseline;
}
</style>