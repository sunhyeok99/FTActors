<template>
  <div class="modal fade" id="montagePopup" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-fullscreen">
      <div class="modal-content">
        <div class="modal-body">
          <iframe width="1120" height="530" src="https://www.youtube.com/embed/lJXaNYTVjrQ?si=jZHMoe0Tu1yo4tPb"
            title="YouTube video player" frameborder="0" id="player" videoid="p1"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            allowfullscreen></iframe>
            <button type="button" class="btn" id="whitebtn" data-bs-dismiss="modal">CLOSE</button>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>

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


</style>