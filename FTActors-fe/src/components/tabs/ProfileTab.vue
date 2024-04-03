<template>
  <div class="wrapper">
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col-lg-3">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"
          xmlns="@/assets/icons/DefaultProfile.png" role="img" aria-label="Placeholder: 140x140"
          preserveAspectRatio="xMidYMid slice" focusable="false">
          <title>Placeholder</title>
          <rect width="100%" height="100%" fill="#777"></rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
        </svg>

        <h2>배우 배사람</h2>
        <p> 보기</p>
        <p><a class="btn btn-secondary" href="#" @click="goToActorProfile">보기</a></p>
      </div>
      <div class="col-lg-3">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"
          xmlns="@/assets/icons/DefaultProfile.png" role="img" aria-label="Placeholder: 140x140"
          preserveAspectRatio="xMidYMid slice" focusable="false">
          <title>Placeholder</title>
          <rect width="100%" height="100%" fill="#777"></rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
        </svg>

        <h2>감독 배사람</h2>
        <p>
          보기
        </p>
        <p><a class="btn btn-secondary" href="#" @click="goToDirectorProfile">보기</a></p>
      </div>
      <div class="col-lg-3">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"
          xmlns="@/assets/icons/DefaultProfile.png" role="img" aria-label="Placeholder: 140x140"
          preserveAspectRatio="xMidYMid slice" focusable="false">
          <title>Placeholder</title>
          <rect width="100%" height="100%" fill="#777"></rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
        </svg>

        <h2>감독 배사람</h2>
        <p>
          프로필이 존재하지 않습니다
        </p>

       <DirectorProfileModal />
      </div>
      <div class="col-lg-3">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"
          xmlns="@/assets/icons/DefaultProfile.png" role="img" aria-label="Placeholder: 140x140"
          preserveAspectRatio="xMidYMid slice" focusable="false">
          <title>Placeholder</title>
          <rect width="100%" height="100%" fill="#777"></rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
        </svg>

        <h2>배우 배사람</h2>
        <p>
          프로필이 존재하지 않습니다 </p>

          <ActorProfileModal/>
      </div>

    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import ActorProfileModal from '../modals/ActorProfileModal.vue';
import DirectorProfileModal from '../modals/DirectorProfileModal.vue';
const router = useRouter();
const goToActorProfile = () => {
  router.push({ name: 'actorprofile' });
};

const goToDirectorProfile = () => {
  router.push({ name: 'directorprofile' });
};



</script>

<style>
.wrapper {
  padding-top: 2rem;
  padding-bottom: 2rem;
}

.col {
  padding: 1rem;
}
</style>