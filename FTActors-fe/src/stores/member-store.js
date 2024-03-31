import { defineStore } from "pinia";
import { ref } from "vue";

export const useMemberStore = defineStore("member", () => {
  // 로컬 스토리지에서 사용자 정보를 불러오는 함수
  const loadUserInfo = () => {
    const storedMemberInfo = localStorage.getItem("memberInfo");
    return storedMemberInfo ? JSON.parse(storedMemberInfo) : null;
  };

  const memberInfo = ref(loadMemberInfo() || "");
  const isAuthenticated = ref(!!memberInfo.value);

  function setUser(member) {
    memberInfo.value = member;
    localStorage.setItem("memberInfo", JSON.stringify(member));
    isAuthenticated.value = !!member;
  }

  function clearUser() {
    memberInfo.value = null;
    localStorage.removeItem("memberInfo");
    isAuthenticated.value = false;
  }

  return { userInfo, isAuthenticated, setUser, clearUser };
});

export const useJwtStore = defineStore("jwt", () => {
  const token = ref(localStorage.getItem("jwt-token") || "");

  function setToken(jwt) {
    token.value = jwt;
    localStorage.setItem("jwt-token", jwt);
  }

  function deleteToken() {
    token.value = "";
    localStorage.removeItem("jwt-token");
  }

  return { token, setToken, deleteToken };
});

