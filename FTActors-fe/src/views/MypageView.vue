
<template>
  <div>
    <h1><b>마이페이지</b></h1>
  </div>
  <div class="profile">
    <div class="profilephoto"> <img src="@/assets/DefaultProfile.png" alt=""></div>
    <div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item"><label><b>이름</b></label> 배사람 </li>
        <li class="list-group-item"><label><b>닉네임</b></label>춤추는 산양 </li>
        <li class="list-group-item"><label><b>이메일</b></label> bausa@gmail.com </li>
        <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
      </ul>
    </div>
  </div>
  <div class="tabs">
    <nav>
      <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <button class="nav-link active" id="nav-profile-tab" data-bs-toggle="tab" data-bs-target="#nav-profile"
          type="button" role="tab" aria-controls="nav-profile" aria-selected="true">프로필</button>
        <button class="nav-link" id="nav-board-tab" data-bs-toggle="tab" data-bs-target="#nav-board" type="button"
          role="tab" aria-controls="nav-board" aria-selected="false">공고</button>
        <button class="nav-link" id="nav-montage-tab" data-bs-toggle="tab" data-bs-target="#nav-montage" type="button"
          role="tab" aria-controls="nav-montage" aria-selected="false">몽타쥬</button>
        <button class="nav-link" id="nav-companion-tab" data-bs-toggle="tab" data-bs-target="#nav-companion" type="button"
          role="tab" aria-controls="nav-companion" aria-selected="false">동료</button>
      </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab"
        tabindex="0">
        <ProfileTab />
      </div>
      <div class="tab-pane fade" id="nav-board" role="tabpanel" aria-labelledby="nav-board-tab" tabindex="0">
        <BoardTab />
      </div>
      <div class="tab-pane fade" id="nav-montage" role="tabpanel" aria-labelledby="nav-montage-tab" tabindex="0">
        <MontageTab />
      </div>
      <div class="tab-pane fade" id="nav-companion" role="tabpanel" aria-labelledby="nav-companion-tab" tabindex="0">
        <CompanionTab />
      </div>
    </div>
  </div>

</template>

<script setup>
import ProfileTab from '@/components/tabs/ProfileTab.vue';
import BoardTab from '@/components/tabs/BoardTab.vue';
import MontageTab from '@/components/tabs/MontageTab.vue';
import CompanionTab from '@/components/tabs/CompanionTab.vue';

</script>

<style>
.profile {
  display: flex;
  height: 10rem;
}

.profilephoto img {
  width: 10rem;
  height: 10rem;
}

.list-group-item {
  display: flex;
  justify-content: space-between;
}



.tabs {
  margin-top: 40px;
  margin-bottom: 40px;
}
</style>