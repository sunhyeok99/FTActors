<template>
  <div class="modal fade" id="montagePopup" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-fullscreen">
      <div class="modal-content">
        <div class="modal-body">
          <div class="montagethumbnail">
            <img src="@/assets/WhiteNext.png" alt="" id="previous" @click="goToPreviousMontage">
            <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
              title="YouTube video player" frameborder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
              allowfullscreen></iframe>
            <img src="@/assets/WhiteNext.png" alt=""id="next" @click="goToNextMontage">
          </div>
            <button type="button" class="btn" id="whitebtn" data-bs-dismiss="modal">CLOSE</button>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter();
const goToPreviousMontage = () => {
  router.push({ name: 'join' });
};
const goToNextMontage = () => {
  router.push({ name: 'join' });
};
</script>

<style scoped>

.modal-body {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
}
.modal-content {
  background-color: black;
  
}

.modal-footer {
  border-top: 0;
}

#whitebtn {
  color: white;
  width: 10rem;
} 
#previous {
  transform: rotate(180deg);
  width: 4rem;
  height: 4rem;
  cursor: pointer;
}
#next {
  width: 4rem;
  height: 4rem;
  cursor:pointer;
}

</style>