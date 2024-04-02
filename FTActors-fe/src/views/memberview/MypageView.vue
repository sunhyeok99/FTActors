<template>
  <div class="mypage">
    <div class="mypage-header">
      <h1><b>마이페이지</b></h1>
    </div>
    <div class="profile">
      <div class="profilephoto">  <img :src="member.image" alt=""></div>
      <div class="profilelist">
        <ul class="list-group list-group-flush">
          <li class="list-group-item"><label><b>이름</b></label> 배사람

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
            type="button" role="tab" aria-controls="nav-profile" aria-selected="true">프로필</button>
            <!-- 공고 -->
          <button class="nav-link dropdown" id="nav-board-tab" data-bs-toggle="tab" data-bs-target="#nav-board"
            type="button" role="tab" aria-controls="nav-board" aria-selected="false">
            <p class="nav-link dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false"
              id="highest">공고</p>
            <ul class="dropdown-menu">
              <li><p class="dropdown-item" @click="getWishlist">찜한 공고</p></li>
              <li><p class="dropdown-item" @click="getApplylist">지원한 공고</p></li>
              <li><p class="dropdown-item" @click="getPostlist">게시한 공고</p></li>
            </ul>
          </button>
          <!-- 몽타쥬 -->
          <button class="nav-link" id="nav-companion-tab" data-bs-toggle="tab" data-bs-target="#nav-montage" type="button"
            role="tab" aria-controls="nav-companion" aria-selected="false">몽타쥬</button>
            <!-- 동료 -->
            <button class="nav-link dropdown" id="nav-board-tab" data-bs-toggle="tab" data-bs-target="#nav-companion"
            type="button" role="tab" aria-controls="nav-board" aria-selected="false">
            <p class="nav-link dropdown-toggle" data-bs-toggle="dropdown" role="button" aria-expanded="false"
              id="highest">동료</p>
            <ul class="dropdown-menu">
              <li>
                <p class="dropdown-item" @click="getFollowingList">팔로우</p>
              </li>
              <li>
                <p class="dropdown-item" @click="getFollowerList">팔로잉</p>
              </li>

            </ul>
          </button>
        </div>
      </nav>
      <!-- 마이페이지 탭 콘텐츠 -->
<div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab"
        tabindex="0">
        <ProfileTab />
    </div>
    <div class="tab-pane fade show active" id="nav-board" role="tabpanel" aria-labelledby="nav-board-tab" tabindex="1"
        v-show="recruitments.length > 0">
        <div class="col" v-for="recruitment in recruitments" :key="recruitment.id">
            <div class="card" id="board">
                <img :src="recruitment.image" alt="" @click="goToBoardDetail(recruitment.id)">
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
                <img :src="post.image" alt="" @click="goToApplyList(post.id)">
                <div class="card-body" @click="goToApplyList(post.id)">
                    <h5 class="card-title"><b>{{ post.title }}</b></h5>
                    <p class="card-text">종료일자: {{ post.endDate }}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="nav-montage" role="tabpanel" aria-labelledby="nav-montage-tab" tabindex="2">
        <MontageTab />
    </div>
    <div class="tab-pane fade" id="nav-companion" role="tabpanel" aria-labelledby="nav-companion-tab"
        tabindex="3">
        <div class="tab-pane fade show active" id="nav-postlist" role="tabpanel" aria-labelledby="nav-follow-tab" tabindex="1"
            v-if="followings.length > 0">
            <div class="col" v-for="following in followings" :key="following.id">
                <div class="card-body" @click="goToProfileDetail(following.id)">
                    <h5 class="card-title"><b>{{ following.memberName }}</b></h5>
                    <button class="like-btn" @click="changeFollow(following.followingId, following.followerId)">
                        <p v-if="following.follow === 1">삭제</p>
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
                        <p v-if="follower.follow === 1">삭제</p>
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
import { recruitmentApi, memberApi, followApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";
  
  const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
const router = useRouter();

const member = ref({});
const recruitments = ref({});
const applys = ref({});
const posts = ref({});

const followings = ref({});
const followers = ref({});


const isPrivate = ref(false)
const followingNum = ref(0); // 초기값을 0으로 설정
const followerNum = ref(0); // 초기값을 0으로 설정

const fetchMember = async () => {
  const id = loginMember.value
      const response = await memberApi.getById(id);
      member.value = response.data.data;
      fetchFollow();
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

const getFollowingList = async () => {
  try {
    const response = await followApi.followingList(loginMember.value);
    console.log(response.data);
    followings.value = response.data.data;
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

const changeFollow = async (followingId, followerId, index) => {
  try {
      const response = await followApi.following(followingId, followerId);
      if(response.data.status == 200){
        if(response.data.data == 0){
          alert('팔로잉을 취소하였습니다.')
        }
        else{
          alert('팔로잉을 하였습니다.')
        }
        const tmp = followings.value.find(following => following.id === index);
        if(tmp == 0 || tmp == null){
          tmp.follow = response.data.data;   
          // 여기 다시 해야함
        }
        tmp.follow = response.data.data;
        console.log(tmp.follow)
      }
    
    } catch (error) {
      console.error('Error toggling like:', error);
  }
};

</script>

<style>
.mypage {
  padding: 2rem;
}

.mypage-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
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

.nav-link.dropdown {
  position: relative;
  /* z-index가 적용되도록 position 속성 추가 */
}
.dropdown-menu {
  position: absolute;
  top: 100%; /* 드롭다운 메뉴를 항상 네비게이션 링크 아래에 배치합니다 */
  z-index: 9999;
}

#nav-board-tab {
  border: none;
  background: none;
  padding: 0;
}

#highest {
  z-index: 1050;
}

.tabs {
  margin-top: 40px;
  margin-bottom: 40px;
}
</style>