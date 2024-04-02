<template>
  <div class="container my-5">
    <div class="profileheader text-center mb-5">
      <h1><b>BOARD</b></h1>
    </div>
    <div class="row">
      <div class="col-md-4">
        <img :src="recruitment.image" alt="" class="img-fluid">
      </div>
      <div class="col-md-8">
        <div class="profilelist">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">
              <div class="d-flex justify-content-between align-items-center">
                <label><b>{{ recruitment.title }}</b></label>
                <template v-if="checkPermission()">
                  <div>
                    <button type="button" class="btn btn-outline-dark" @click="boardUpdate">공고 변경</button>
                    <button type="button" class="btn btn-danger" @click="confirmDelete">공고 삭제</button>
                  </div>
                </template>
                <template v-else>
                  <button v-if="recruitment.apply === 1" class="btn btn-secondary">이미 지원하였습니다</button>
                  <button v-else @click="apply" class="btn btn-primary">지원하기</button>
                </template>
              </div>
            </li>
            <li class="list-group-item"><b>담당자:</b> {{ recruitment.postMember }}</li>
            <li class="list-group-item"><b>공고분류:</b> {{ recruitment.category }}</li>
            <li class="list-group-item"><b>지원시작일자:</b> {{ recruitment.startDate }}</li>
            <li class="list-group-item"><b>지원마감일자:</b> {{ recruitment.endDate }}</li>
          </ul>
        </div>
      </div>
    </div>
    <div class="detailboardpage mt-4">
      <h4><b>공고 내용</b></h4>
      <p>{{ recruitment.content }}</p>
      <h5><b>첨부파일</b></h5>
      <a :href="recruitment.file" download="recruitment_file">파일 다운로드</a>
      <p class="mt-2"> 영상을 올릴 때 꼭 [이름]배역이름으로 파일 명을 지정해주세요.<br>ex) [배역이름]실제이름</p>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { recruitmentApi } from '@/util/axios';
import { useMemberStore } from "@/stores/member-store.js";

const MemberStore = useMemberStore();
const loginMember = ref(null);
loginMember.value = MemberStore.memberInfo;
const adminId = 1;
const router = useRouter();
const recruitment = ref({});

const fetchRecruitmentDetail = async () => {
  const recruitmentId = router.currentRoute.value.params.id; // 현재 라우트의 파라미터 사용
  if (loginMember.value == "" || loginMember.value == null) {
    const response = await recruitmentApi.getDetail(recruitmentId, adminId);
    recruitment.value = response.data.data;
  } else {
    const memberId = loginMember.value;
    const response = await recruitmentApi.getDetail(recruitmentId, memberId);
    recruitment.value = response.data.data;
  }
};

onMounted(fetchRecruitmentDetail);

const checkPermission = () => {
  return loginMember.value == recruitment.value.postMemberId;
};

const boardUpdate = () => {
  const boardId = recruitment.value.id;
  router.push({ name: 'boardUpdate', state: { id: boardId } });
};

const confirmDelete = () => {
  if (confirm("정말로 삭제하시겠습니까?")) {
    deleteRecruitment();
  }
};

const apply = () => {
  if (loginMember.value == "" || loginMember.value == null) {
    alert("로그인이 필요합니다")
    router.push({ name: 'login' });
  } else {
    const recruitmentId = recruitment.value.id;
    router.push({ name: 'applyCreate', params: { recruitmentId: recruitmentId, memberId: loginMember.value } });
  }
};

const deleteRecruitment = async () => {
  const recruitmentId = recruitment.value.id;
  try {
    const response = await recruitmentApi.remove(recruitmentId);
    if (response.status === 200) {
      alert("삭제되었습니다.");
    } else {
      alert("삭제 실패했습니다.");
    }
    router.push({ name: 'board' });
  } catch (error) {
    console.error("Error deleting recruitment:", error);
  }
};

</script>

<style>
h1 {
  font-size: 4rem;
}

.profileheader {
  display: flex;
  align-items: center;
  font-size: 4rem;
}


.profiletab img {
  width: 24px;
  height: 24px;

}

.profilepage {
  display: flex;
}

.profilelist {
  width: 30rem;
}

.detailprofilepage {
  width: 50%;
}
.uploader {
  display: flex;
  flex-direction: row;
}

#title {
  display: flex;
}
#title button{
  margin-left: auto;
}
</style>
