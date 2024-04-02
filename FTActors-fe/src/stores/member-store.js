import { defineStore } from "pinia";
import { ref } from "vue";

export const useMemberStore = defineStore("member", () => {
  // 로컬 스토리지에서 사용자 정보를 불러오는 함수
  const loadMemberInfo = () => {
    const storedMemberInfo = localStorage.getItem("memberInfo");
    return storedMemberInfo ? storedMemberInfo : null;
  };

  const memberInfo = ref(loadMemberInfo() || "");
  const isAuthenticated = ref(!!memberInfo.value);

  function setUser(member) {
    memberInfo.value = member;
    localStorage.setItem("memberInfo", member);
    isAuthenticated.value = !!member;
  }

  function clearUser() {
    memberInfo.value = null;
    localStorage.removeItem("memberInfo");
    isAuthenticated.value = false;
  }

  return { memberInfo, isAuthenticated, setUser, clearUser };
});

export const useJwtStore = defineStore("jwt", () => {
  const getToken = () => {
    const storedMemberInfo = localStorage.getItem("token");
    return storedMemberInfo ? storedMemberInfo : null;
  };
  const token = ref(getToken() || "");

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

