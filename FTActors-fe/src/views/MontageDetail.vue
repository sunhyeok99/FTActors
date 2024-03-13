<template>
  <div class="montagepage">
    <div class="montagethumbnail">
      <img src="@/assets/Next.png" alt="" id="previous" @click="goToPreviousMontage">
      <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
        title="YouTube video player" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen></iframe>
      <img src="@/assets/Next.png" alt=""id="next" @click="goToNextMontage">
    </div>
    <div class="montagedetail">
      <ul class="list-group list-group-flush">
        <li class="list-group-item"><label><b>출연배우</b></label>한재혁</li>
        <li class="list-group-item"><label><b>작품명</b></label>연극<달동네>
        </li>
      </ul>
    </div>
  </div>
</template>
<script setup>
import { useRouter, useRoute } from 'vue-router'

const router = useRouter();
const route = useRoute();

const goToPreviousMontage = () => {
  // 현재 몽타쥬의 ID를 숫자로 변환하고 1을 빼기
  const currentId = parseInt(route.params.id, 10);
  const previousId = currentId - 1;

  // 이전 몽타쥬로 이동
  router.push({ name: 'montage', params: { id: previousId } });
};

const goToNextMontage = () => {
  // 현재 몽타쥬의 ID를 숫자로 변환하고 1을 더하기
  const currentId = parseInt(route.params.id, 10);
  const nextId = currentId + 1;

  // 다음 몽타쥬로 이동
  router.push({ name: 'montage', params: { id: nextId } });
};

</script>
<style>
.montagepage {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 2rem;
}

.montagethumbnail {
  display: flex;
  align-items: center;
  margin-bottom: 4rem;
}

.montagethumbnail img {
  width: 25px;
  height: 25px;
  margin: 2rem;
}

.montagedetail {
  width: 30rem;
}

#previous {
  transform: rotate(180deg);
  color-interpolation-filters : white;
  cursor: pointer;
}
#next {
  cursor: pointer;
}

</style>