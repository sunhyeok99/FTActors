import axios from "axios";

const BASE_URL = `http://localhost:8080`;

const axiosInstance = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});
const formDataInstance = axios.create({
    baseURL: BASE_URL,
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  });
const memberApi = {
  login: (memberDto) => {
    return axiosInstance.post("/signin",  memberDto );
  },
  signup: (jwtDto) =>
    axiosInstance.post("/signup", jwtDto ),
  updatepassword: (user) =>
    axiosInstance.post("/api/member/updatePassword", null, { params: member }),
};

const recruitmentApi = {
    getList: (memberId) => {    
      return axiosInstance.get("/api/recruitment/list", { params: { memberId } });
    },
    register: (recruitmentDto) => {
      return formDataInstance.post("/api/recruitment", recruitmentDto);
    },
    remove: (recruitmentId) => {
      return axiosInstance.delete("/api/recruitment", { params: { recruitmentId } });
    },
    update: (recruitmentDto) => {
      return formDataInstance.put("/api/recruitment/update", recruitmentDto);
    },
    updateDate: (recruitmentId, endDate) => {
      return axiosInstance.put("/api/recruitment/updateDate", null, {
        params: { recruitmentId, endDate },
      });
    },
    getDetail: (recruitmentId, memberId) => {
      return axiosInstance.get("/api/recruitment/detail", {
        params: { recruitmentId, memberId },
      });
    },
    getPostList: (postMemberId) => {
      return axiosInstance.get("/api/recruitment/postList", {
        params: { postMemberId },
      });
    },
    updateWishlist: (recruitmentId, memberId) => {
      return axiosInstance.put("/api/recruitment/wishlist", null, {
        params: { recruitmentId, memberId },
      });
    },
    getWishlist: (memberId) => {
      return axiosInstance.get("/api/recruitment/wishlist", {
        params: { memberId },
      });
    },
    apply: (applyDto) => {
      return formDataInstance.post("/api/recruitment/apply", applyDto);
    },
    cancelApply: (recruitmentId, memberId) => {
      return axiosInstance.delete("/api/recruitment/apply", {
        params: { recruitmentId, memberId },
      });
    },
    getMyApply: (memberId) => {
      return axiosInstance.get("/api/recruitment/myApply", { params: { memberId } });
    },
    getApplyDetail: (applyId) => {
      return axiosInstance.get("/api/recruitment/applyDetail", { params: { applyId } });
    },
    getApplyList: (recruitmentId) => {
      return axiosInstance.get("/api/recruitment/applyList", {
        params: { recruitmentId },
      });
    },
  };

  const followApi = {
    following: (followingId, followerId) => {
      return axiosInstance.put("/api/follow", null, {
        params: { followingId, followerId },
      });
    },
    followingList: (followingId) => {
      return axiosInstance.get("/api/follow/followingList", {
        params: { followingId },
      });
    },
    followerList: (followerId) => {
      return axiosInstance.get("/api/follow/followerList", {
        params: { followerId },
      });
    },
    followDetail: (followingId, followerId) => {
      return axiosInstance.get("/api/follow/detail", {
        params: { followingId, followerId },
      });
    },
    followingNum: (followingId) => {
      return axiosInstance.get("/api/follow/followingNum", {
        params: { followingId },
      });
    },
    followerNum: (followerId) => {
      return axiosInstance.get("/api/follow/followerNum", {
        params: { followerId },
      });
    },
  };
  

export { memberApi, recruitmentApi, followApi};

