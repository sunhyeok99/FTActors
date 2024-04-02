<template>
  <div class="container h-100">
    <div class="row justify-content-center align-items-start" style="min-height: 100vh;">
      <div class="col-md-6 col-lg-4">
        <div class="mt-5 pt-5">
          <div class="text-center mb-4">
            <h1><b>로그아웃 <br>완료</b></h1>
          </div>
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