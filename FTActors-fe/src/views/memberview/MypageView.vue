<template>
  <div class="mypage">
    <div class="mypage-header">
      <h1><b>마이페이지</b></h1>
    </div>
    <div class="profile">
      <div class="profilephoto">  <img :src="member.image" alt=""></div>
      <div class="profilelist">
        <ul class="list-group list-group-flush">
          <li class="list-group-item"><label><b>이름</b></label> {{member.name}}
          </li>
          <li class="list-group-item"><label><b>닉네임</b></label>{{ member.stageName }} </li>
          <li class="list-group-item"><label><b>이메일</b></label>{{ member.email }} </li>
          <li class="list-group-item"><label><b>전화번호</b></label> {{ member.phone }} </li>
          <li class="list-group-item"><label><b>이름</b></label> {{ member.name }} </li>
          <li class="list-group-item"><label><b>팔로잉수</b></label> {{followingNum}}명 </li>
          <li class="list-group-item"><label><b>팔로워수</b></label> {{ followerNum}}명 </li>
        </ul>
      </div>
    </div>
    <div class="tabs">
      <!-- 마이페이지 탭바 -->
      <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
          <!-- 프로필 -->
          <button class="nav-link active" id="nav-profile-tab" data-bs-toggle="tab" data-bs-target="#nav-profile"
            type="button" role="tab" aria-controls="nav-profile" aria-selected="true" @click="getMyProfile()">프로필</button>
            <!-- 공고 -->
          <button class="nav-link dropdown" id="nav-board-tab" data-bs-toggle="tab" data-bs-target="#nav-board"
            type="button" role="tab" aria-controls="nav-board" aria-selected="false">
            <p class="nav-link dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false"
              id="highest">공고</p>
            <ul class="dropdown-menu">
              <li><p class="dropdown-item" @click="getWishlist()">찜한 공고</p></li>
              <li><p class="dropdown-item" @click="getApplylist()">지원한 공고</p></li>
              <li><p class="dropdown-item" @click="getPostlist()">게시한 공고</p></li>
            </ul>
          </button>
          <!-- 몽타쥬 -->
          <button class="nav-link" id="nav-companion-tab" data-bs-toggle="tab" data-bs-target="#nav-montage" type="button"
            role="tab" aria-controls="nav-companion" aria-selected="false" @click="getMyMontage()">몽타쥬</button>
            <!-- 동료 -->
            <button class="nav-link dropdown" id="nav-board-tab" data-bs-toggle="tab" data-bs-target="#nav-companion"
            type="button" role="tab" aria-controls="nav-board" aria-selected="false">
            <p class="nav-link dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false"
              id="highest">동료</p>
            <ul class="dropdown-menu">
              <li>
                <p class="dropdown-item" @click="getFollowingList">팔로잉</p>
              </li>
              <li>
                <p class="dropdown-item" @click="getFollowerList">팔로워</p>
              </li>
            </ul>
          </button>
        </div>
      </nav>
      <!-- 마이페이지 탭 콘텐츠 -->
<div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab"
        tabindex="0">
        
        <div class="wrapper">
    <div class="row row-cols-1 row-cols-md-4 g-4">
      <div class="col-lg-3">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"
          xmlns="@/assets/icons/DefaultProfile.png" role="img" aria-label="Placeholder: 140x140"
          preserveAspectRatio="xMidYMid slice" focusable="false">
          <title>Placeholder</title>
          <rect width="100%" height="100%" fill="#777"></rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
        </svg>

        <h2>감독</h2>
        <div v-if="director > 0">
          <p>보기</p>
          <p><a class="btn btn-secondary" href="#" @click="goToProfile(director)">보기</a></p>
        </div>
        <div v-else>
          <p>프로필이 존재하지 않습니다</p>
          <DirectorProfileModal />
        </div>
      </div>

      <div class="col-lg-3">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140"
          xmlns="@/assets/icons/DefaultProfile.png" role="img" aria-label="Placeholder: 140x140"
          preserveAspectRatio="xMidYMid slice" focusable="false">
          <title>Placeholder</title>
          <rect width="100%" height="100%" fill="#777"></rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
        </svg>

        <h2>배우</h2>
        <div v-if="actor > 0">
          <p> 보기</p>
          <p><a class="btn btn-secondary" href="#" @click="goToProfile(actor)">보기</a></p>
        </div>
        <div v-else>
          <p>프로필이 존재하지 않습니다</p>
          <ActorProfileModal />
        </div>
      </div>
    </div>
  </div>

    </div>
    <div class="tab-pane fade show active" id="nav-board" role="tabpanel" aria-labelledby="nav-board-tab" tabindex="1"
        v-show="recruitments.length > 0">
        <div class="col" v-for="recruitment in recruitments" :key="recruitment.id">
            <div class="card" id="board">
                <img :src="recruitment.image" alt="" @click="goToBoardDetail(recruitment.id)" width="300px" height="200px">
                <div class="card-body" @click="goToBoardDetail(recruitment.id)">
                    <h5 class="card-title"><b>{{ recruitment.title }}</b></h5>
                    <p class="card-text">종료일자: {{ recruitment.endDate }}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade show active" id="nav-applylist" role="tabpanel" aria-labelledby="nav-apply-tab" tabindex="1"
        v-show="applys.length > 0">
        <div class="col" v-for="apply in applys" :key="apply.id">
            <div class="card" id="apply">
                <div class="card-body" @click="goToApplyDetail(apply.id)">
                    <h5 class="card-title"><b>공고 제목 : {{ apply.recruitmentTitle }}</b></h5>
                    <p class="card-text">지원날짜: {{ apply.createdAt }}</p>
                    <h5><b>첨부파일</b></h5>
                    <p>
                        <a :href="apply.videoLink" download="apply_file">파일 다운로드</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade show active" id="nav-postlist" role="tabpanel" aria-labelledby="nav-post-tab" tabindex="1"
        v-show="posts.length > 0">
        <div class="col" v-for="post in posts" :key="post.id">
            <div class="card" id="posts">
                <img :src="post.image" alt="" @click="goToApplyList(post.id)" width="300px" height="200px">
                <div class="card-body" @click="goToApplyList(post.id)">
                    <h5 class="card-title"><b>{{ post.title }}</b></h5>
                    <p class="card-text">종료일자: {{ post.endDate }}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="nav-montage" role="tabpanel" aria-labelledby="nav-montage-tab" tabindex="2">
   
   
      <div class="tab-pane fade show active" id="nav-montageList" role="tabpanel" aria-labelledby="nav-follow-tab" tabindex="1"
            v-if="montages.length > 0">
            <div class="col" v-for="montage in montages" :key="montage.id">
                <div class="card-body" @click="goToMontageDetail(montage.id)">
                    <h5 class="card-title"><b>{{ montage.title }}</b></h5>
                    <video controls style="width: 100%;">
                <source :src="montage.link" type="video/mp4" width="300px" height="200px">
                </video>
                </div>
            </div>
          </div>
    </div>
    <div class="tab-pane fade" id="nav-companion" role="tabpanel" aria-labelledby="nav-companion-tab"
        tabindex="3">
        <div class="tab-pane fade show active" id="nav-postlist" role="tabpanel" aria-labelledby="nav-follow-tab" tabindex="1"
            v-if="followings.length > 0">
            <div class="col" v-for="following in followings" :key="following.id">
                <div class="card-body" @click="goToProfileDetail(following.id)">
                    <h5 class="card-title"><b>{{ following.memberName }}</b></h5>
                    <button class="like-btn" @click="changeFollow(following.followingId, following.followerId)">
                        <p v-if="following.follow === 1">팔로잉 삭제</p>
                        <p v-else>팔로잉</p>
                    </button>
                </div>
            </div>
        </div>
        <div class="tab-pane fade show active" id="nav-postlist" role="tabpanel" aria-labelledby="nav-follow-tab" tabindex="1"
            v-if="followers.length > 0">
            <div class="col" v-for="follower in followers" :key="follower.id">
                <div class="card-body" @click="goToProfileDetail(follower.id)">
                    <h5 class="card-title"><b>{{ follower.memberName }}</b></h5>
                    <button class="like-btn" @click="changeFollow(follower.followingId, follower.followerId)">
                        <p v-if="follower.follow === 1">팔로잉 삭제</p>
                        <p v-else>팔로잉</p>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
    </div>
  </div>
</template>

<script setup>
import ProfileTab from '@/components/tabs/ProfileTab.vue';
import MontageTab from '@/components/tabs/MontageTab.vue';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { recruitmentApi, memberApi, followApi, profileApi, montageApi } from '@/util/axios';
import { useMemberStore, useJwtStore } from "@/stores/member-store.js";
  

import ActorProfileModal from '@/components/modals/ActorProfileModal.vue';
import DirectorProfileModal from '@/components/modals/DirectorProfileModal.vue';

const MemberStore = useMemberStore();
const JWTStore = useJwtStore();

const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
const router = useRouter();

const member = ref({});
const recruitments = ref({});
const applys = ref({});
const posts = ref({});
const montages = ref({});

const followings = ref({});
const followers = ref({});


const isPrivate = ref(false)
const followingNum = ref(0); // 초기값을 0으로 설정
const followerNum = ref(0); // 초기값을 0으로 설정

const actor = ref(0);
const director = ref(0);
const fetchMember = async () => {
  const id = loginMember.value
      const response = await memberApi.getById(id);
      member.value = response.data.data;
      fetchFollow();
      getMyProfile();
  };
  const fetchFollow = async () => {
    const id = loginMember.value;
  try {
    const followingResponse = await followApi.followingNum(id);
    followingNum.value = followingResponse.data.data;
    
    const followerResponse = await followApi.followerNum(id);
    followerNum.value = followerResponse.data.data;
  } catch (error) {
    console.error('Error fetching following and follower numbers:', error);
  }
  };

  onMounted(fetchMember);

  const getWishlist = async () => {
  try {
    const response = await recruitmentApi.getWishlist(loginMember.value);
    console.log(response.data);
    recruitments.value = response.data.data
    applys.value = [];
    posts.value = [];
  } catch (error) {
    console.error("Error fetching wishlist:", error);
  }
};


const getApplylist = async () => {
  try {
    const response = await recruitmentApi.getMyApply(loginMember.value);
    console.log(response.data);
    applys.value = response.data.data;
    recruitments.value = [];
    posts.value = [];
  } catch (error) {
    console.error("Error fetching wishlist:", error);
  }
};

const getPostlist = async () => {
  try {
    const response = await recruitmentApi.getPostList(loginMember.value);
    console.log(response.data);
    posts.value = response.data.data;
    recruitments.value = [];
    applys.value = [];
  } catch (error) {
    console.error("Error fetching wishlist:", error);
  }
};

const getMyMontage = async () => {
  try {
    const response = await montageApi.MyMontageList();
    console.log(response.data.data);
    montages.value = response.data.data;
    posts.value = [];
    recruitments.value = [];
    applys.value = [];
  } catch (error) {
    console.error( error);
  }
}


const getFollowingList = async () => {
  try {
    const response = await followApi.followingList(loginMember.value);
    console.log(response.data);
    followings.value = response.data.data;
    posts.value = [];
    recruitments.value = [];
    applys.value = [];
    followers.value = [];
  } catch (error) {
    console.error("Error fetching wishlist:", error);
  }
};

const getFollowerList = async () => {
  try {
    const response = await followApi.followerList(loginMember.value);
    console.log(response.data);
    followers.value = response.data.data;
    posts.value = [];
    recruitments.value = [];
    applys.value = [];
    followings.value = [];
  } catch (error) {
    console.error("Error fetching wishlist:", error);
  }
};

const goToBoardDetail = (boardId) => {
  router.push({ name: 'boardDetail', params: { id: boardId } });
};

const goToApplyDetail = (applyId) => {
  router.push({ name: 'applyDetail', params: { id: applyId } });
};

const goToApplyList = (postId) => {
  router.push({ name: 'applyList', params: { id : postId } });
};
const goToMontageDetail = (montageId) => {
  router.push({ name: 'montagedetail', params: { id : montageId } });
};

const changeFollow = async (followingId, followerId) => {
  try {
      const response = await followApi.following(followingId, followerId);
      if(response.data.status == 200){
        if(response.data.data == 0){
          alert('팔로잉을 취소하였습니다.')
        }
        else{
          alert('팔로잉을 하였습니다.')
        } 
        console.log(response.data)
      }
    
    } catch (error) {
      console.error('Error toggling like:', error);
  }
};

const goToProfile = (profileId) => {
     router.push({ name : 'profileDetail' , params : { id : profileId}})
};

const goToProfileDetail = async (memberId) => {
  try {
    const response = await profileApi.searchById(memberId);
    console.log(response.data.data);
    // 응답에 필요한 데이터가 포함되어 있다고 가정합니다.
    const profileStatus = response.data.data;
    // profileStatus에 따라 actor 및 director 값을 업데이트합니다.
    const a = 0;
    const b = 0;
    if (profileStatus[0] > 0) { // 배우
      a = 1;
    } 
    if(profileStatus[1] > 0){
      b= 1;
    }
    if(a ==1 && b==1){
      const choice = confirm('해당 멤버는 감독과 배우 프로필 모두 존재합니다.\n\n"확인"을 클릭하면 배우 프로필 페이지로 이동합니다.\n\n"취소"를 클릭하면 감독 프로필 페이지로 이동합니다.');

    // 선택에 따라 이동할 프로필 페이지 결정
      if (choice) {
          // 배우 프로필 페이지로 이동
          router.push({ name: 'profileDetail', params: { id: profileStatus[0] }});
      } else {
          // 감독 프로필 페이지로 이동
          router.push({ name: 'profileDetail', params: { id: profileStatus[1] }});
      }
    }
    else if(a==1){
      alert('해당 멤버의 배우 페이지로 이동합니다.');
    router.push({ name : 'profileDetail' , params : { id : profileStatus[0]}})
    }
    else if(b ==1){
      alert('해당 멤버의 감독 페이지로 이동합니다.');
     router.push({ name : 'profileDetail' , params : { id : profileStatus[1]}})
    }
    else{
      alert('해당 멤버는 프로필이 없습니다.');
    }

  } catch (error) {
    console.error('프로필 상태를 가져오는 중 오류 발생:', error);
  }
};

const getMyProfile = async () => {
  try {
    const response = await profileApi.searchById(loginMember.value);
    console.log(response.data.data);
    // 응답에 필요한 데이터가 포함되어 있다고 가정합니다.
    const profileStatus = response.data.data;
    // profileStatus에 따라 actor 및 director 값을 업데이트합니다.
    if (profileStatus[0] > 0) { // 배우
      actor.value = profileStatus[0];
    } 
    if(profileStatus[1] > 0){
      director.value = profileStatus[1];
    }
    posts.value = [];
    recruitments.value = [];
    applys.value = [];

  } catch (error) {
    console.error('프로필 상태를 가져오는 중 오류 발생:', error);
  }
};



</script>

<style>
.mypage {
  padding: 1rem; /* 패딩을 조금 줄임 */
}

.mypage-header {
  margin-bottom: 1rem; /* 제목 아래의 마진을 줄임 */
}

.profile {
  display: flex;
  height: auto; /* 고정 높이를 auto로 변경하여 내용에 맞게 조절 */
  margin-top: 1rem; /* 마진 줄임 */
  margin-bottom: 1rem; /* 마진 줄임 */
  justify-content: center; /* 가운데 정렬로 변경 */
  gap: 20px; /* 프로필 사진과 리스트 사이의 간격 설정 */
}

.profilelist, .profilephoto {
  flex: 1; /* 두 컨테이너가 공간을 동일하게 차지하도록 설정 */
  margin: 0 20px; /* 좌우 마진 설정 */
}

.profilephoto img {
  height: auto; /* 이미지 높이를 자동으로 조절 */
  width: 100%; /* 이미지 너비를 부모 컨테이너에 맞게 조절 */
  max-height: 300px; /* 이미지 최대 높이 설정 */
  object-fit: cover; /* 이미지가 컨테이너를 꽉 채우면서 비율 유지 */
}

.list-group-item {
  padding: 10px 15px; /* 항목 내부 패딩 줄임 */
}

.tabs {
  margin-top: 20px;
  margin-bottom: 20px;
}

.nav-link.dropdown, .dropdown-menu {
  margin-top: 0; /* 드롭다운 메뉴의 상단 마진을 제거 */
}

</style>