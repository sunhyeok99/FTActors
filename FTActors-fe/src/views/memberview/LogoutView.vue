<template>
    <div class="row">
      <div class="col-md-3">
        <div>
          <div>
            <h1><b>로그아웃 <br>완료</b></h1>
          </div>
        </div>
      </div>
    </div>
    
  </template>
  
  <script setup>
    import { useMemberStore } from "@/stores/member-store.js";
  
  const MemberStore = useMemberStore();
MemberStore.clearUser();
  </script>
  <style>
  
  </style>