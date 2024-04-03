import axios from "axios";
import { useJwtStore } from "@/stores/member-store";

const BASE_URL = `http://localhost:8080`;

const SERVER_URL = 'https://j10a602.p.ssafy.io/api';

axios.interceptors.request.use(
  config => {
    // 인증 토큰을 여기에서 설정하거나 수정합니다.
    
    const jwtStore = useJwtStore();
    const token = jwtStore.getToken();
    
    config.headers.Authorization = token == undefined ? '' : `Bearer ${token}`;
    
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

const axiosInstance = axios.create({
  baseURL: SERVER_URL,
  headers: {
    "Content-Type": "application/json",
  },
});
const formDataInstance = axios.create({
    baseURL: SERVER_URL,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
const memberApi = {
  login: (memberDto) => {
    return axiosInstance.post("/signin",  memberDto );
  },
  signup: (jwtDto) =>
    formDataInstance.post("/signup", jwtDto),
  updatepassword: (user) =>
    axiosInstance.post("/api/member/updatePassword", null, { params: member }),
    getById: (id) => {
    return axiosInstance.get("/loginmember", { params: { id } })
    },
};

const recruitmentApi = {
    getList: (memberId) => {    
      return axiosInstance.get("/recruitment/list", { params: { memberId } });
    },
    register: (recruitmentDto) => {
      return formDataInstance.post("/recruitment", recruitmentDto);
    },
    remove: (recruitmentId) => {
      return axiosInstance.delete("/recruitment", { params: { recruitmentId } });
    },
    update: (recruitmentDto) => {
      return formDataInstance.put("/recruitment/update", recruitmentDto);
    },
    updateDate: (recruitmentId, endDate) => {
      return axiosInstance.put("/recruitment/updateDate", {
        params: { recruitmentId, endDate },
      });
    },
    getDetail: (recruitmentId, memberId) => {
      return axiosInstance.get("/recruitment/detail", {
        params: { recruitmentId, memberId },
      });
    },
    getPostList: (postMemberId) => {
      return axiosInstance.get("/recruitment/postList", {
        params: { postMemberId },
      });
    },
    updateWishlist: (recruitmentId, memberId) => {
      return axiosInstance.put("/recruitment/wishlist", null,  {
        params: { recruitmentId, memberId },
      });
    },
    getWishlist: (memberId) => {
      return axiosInstance.get("/recruitment/wishlist", {
        params: { memberId },
      });
    },
    apply: (applyDto) => {
      return formDataInstance.post("/recruitment/apply", applyDto);
    },
    cancelApply: (recruitmentId, memberId) => {
      return axiosInstance.delete("/recruitment/apply", {
        params: { recruitmentId, memberId },
      });
    },
    getMyApply: (memberId) => {
      return axiosInstance.get("/recruitment/myApply", { params: { memberId } });
    },
    getApplyDetail: (applyId) => {
      return axiosInstance.get("/recruitment/applyDetail", { params: { applyId } });
    },
    getApplyList: (recruitmentId) => {
      return axiosInstance.get("/recruitment/applyList", {
        params: { recruitmentId },
      });
    },
  };

  const followApi = {
    following: (followingId, followerId) => {
      return axiosInstance.put("/follow", null, {
        params: { followingId, followerId },
      });
    },
    followingList: (followingId) => {
      return axiosInstance.get("/follow/followingList", {
        params: { followingId },
      });
    },
    followerList: (followerId) => {
      return axiosInstance.get("/follow/followerList", {
        params: { followerId },
      });
    },
    followDetail: (followingId, followerId) => {
      return axiosInstance.get("/follow/detail", {
        params: { followingId, followerId },
      });
    },
    followingNum: (followingId) => {
      return axiosInstance.get("/follow/followingNum", {
        params: { followingId },
      });
    },
    followerNum: (followerId) => {
      return axiosInstance.get("/follow/followerNum", {
        params: { followerId },
      });
    },
  };
  
  const chatApi = {
    createChatRoom: () => {
      return axiosInstance.post("/api/chat");
    },
    getMyChatRooms: (memberId) => {
      return axiosInstance.get("/api/chat/room/mylist", {
        params: { memberId },
      });
    },
    joinChatRoom: (roomId, memberId) => {
      return axiosInstance.post("/api/chat/room/in", {
        params: { roomId, memberId },
      });
    },
    exitChatRoom: (roomId, memberId) => {
      return axiosInstance.delete("/api/chat/room/out", {
        params: { roomId, memberId },
      });
    },
  };
  const profileApi = { // 프로필 api
    getAllProfileList: (sorting = 1) => {  // 소팅 값이 따로 안 들어오면 1로 세팅 (1, 2중 가능)
      return axiosInstance.get("/profile/list", { params: { sort: sorting } });
    },
    getProfileList: () => {  
      return axiosInstance.get("/profile");
    },
    createProfile: (profileRequest) => {
      return formDataInstance.post("/profile/myprofile", profileRequest);
    },

    removeProfile: (profileId) => {
      return axiosInstance.delete("/profile/myprofile", { params: { profile_id : profileId } });
    },
    modifyProfile: (profileRequest) => {
      return formDataInstance.put("/profile/myprofile",  profileRequest );
    },
    
    getDetailProfile: (profileId) => {
      return axiosInstance.get("/profile/detail", {
        params: { profile_id: profileId },
      });
    },

    searchContent: (keywords) => {
      return axiosInstance.get("/profile/searchcontent", { params: { keywords: keywords } });
    },
    searchByStageName: (findName) => {
      return axiosInstance.get("/profile/searchstagename", { params: { stage_name: findName } });
    },
    searchByName: (findName) => {
      return axiosInstance.get("/profile/searchname", { params: { name: findName } });
    },
    searchById: (memberId) => {
      return axiosInstance.get("/profile/getmyprofile", { params: { memberId : memberId } });
    },
  };

  const recommendApi = {
    getRecruitmentList: () => {
      return axiosInstance.post("/recommend/recruitment");
    },
  };
  const montageApi = {
    MyMontageList : () => {
      return axiosInstance.get("/montage/my-montage");
    }
  }

export { memberApi, recruitmentApi, followApi, chatApi, profileApi, recommendApi, montageApi };

