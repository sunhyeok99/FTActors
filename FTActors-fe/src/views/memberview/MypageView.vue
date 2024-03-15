<template>
  <div class="mypage">
  <div>
    <h1><b>마이페이지</b></h1>
  </div>
  <div class="profile">
    <div class="profilephoto"> <img src="@/assets/icons/DefaultProfile.png" alt=""></div>
    <div class="profilelist">
      <ul class="list-group list-group-flush">
        <li class="list-group-item"><label><b>이름</b></label> 배사람 </li>
        <li class="list-group-item"><label><b>닉네임</b></label>춤추는 산양 </li>
        <li class="list-group-item"><label><b>이메일</b></label> bausa@gmail.com </li>
        <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
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
        <button class="nav-link dropdown" id="nav-board-tab" data-bs-toggle="tab" data-bs-target="#nav-board"
          type="button" role="tab" aria-controls="nav-board" aria-selected="false">
          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
            aria-expanded="false">공고</a>
          <ul class="dropdown-menu" id="highest">
            <li><a class="dropdown-item" href="#">찜한 공고</a></li>
            <li><a class="dropdown-item" href="#">지원한 공고</a></li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li><a class="dropdown-item" href="#">올린 공고</a></li>
          </ul>
        </button>
        <button class="nav-link" id="nav-montage-tab" data-bs-toggle="tab" data-bs-target="#nav-montage" type="button"
          role="tab" aria-controls="nav-montage" aria-selected="false">몽타쥬</button>
        <button class="nav-link" id="nav-companion-tab" data-bs-toggle="tab" data-bs-target="#nav-companion"
          type="button" role="tab" aria-controls="nav-companion" aria-selected="false">동료</button>
      </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
      <div class="tab-pane fade show active" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab"
        tabindex="0">
        <ProfileTab />
      </div>
      <div class="tab-pane fade" id="nav-board" role="tabpanel" aria-labelledby="nav-board-tab" tabindex="1">
        <BoardTab />
      </div>
      <div class="tab-pane fade" id="nav-montage" role="tabpanel" aria-labelledby="nav-montage-tab" tabindex="2">
        <MontageTab />
      </div>
      <div class="tab-pane fade" id="nav-companion" role="tabpanel" aria-labelledby="nav-companion-tab" tabindex="3">
        <CompanionTab />
      </div>
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
.mypage {
  padding: 2rem;
}
.profile {
  display: flex;
  height: 32rem;
  margin-top: 2rem;
  margin-bottom: 2rem;
  justify-content: space-around;
}
.profilelist {
  width: 30rem;
}
.profilephoto img {
  height: 30rem;
  margin-left: 2rem;
  margin-right: 2rem;
}

.list-group-item {
  display: flex;
  justify-content: space-between;
}

#nav-board-tab {
  border: none;
  background: none;
  padding: 0;
}

#highest {
  z-index: 10;
}
.tabs {
  margin-top: 40px;
  margin-bottom: 40px;
}
</style>