<template>
    <div class="title">
        <h3><b>큰 몽타쥬</b></h3>
    </div>
    <div class="card" id="board">
        <iframe src="https://www.youtube.com/embed/-rSVUNy8Wbs?si=i5h_YPJFjXe3KKch" title="YouTube video player"
            frameborder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen></iframe>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const goToMontagePage = () => {
    router.push({ name: 'montage' });
};
</script>
<style>
#board {
    border: none;
}

.title {
    display: flex;
}

.pageright {
    display: flex;
    margin-left: auto;
}
</style>