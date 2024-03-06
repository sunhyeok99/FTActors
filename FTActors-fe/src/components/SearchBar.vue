<template>
    <div class="search-bar">
        <select class="form-select form-select-sm" aria-label="Small select example">
            <option selected>Open this select menu</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
        </select>
        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Username" aria-label="Username"
                aria-describedby="basic-addon1">
        </div>
    </div>
</template>

<script setup>
</script>

<style>
.search-bar{
    display: flex;
}
</style>