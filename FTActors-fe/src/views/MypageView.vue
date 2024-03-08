<template>
    <div>
      <h1><b>마이페이지</b></h1>
    </div>
    <div class="profile">
    <div class="profilephoto"> <img src="@/assets/DefaultProfile.png" alt=""></div>
    <div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item"><label><b>이름</b></label> 배사람 </li>
        <li class="list-group-item"><label><b>닉네임</b></label>춤추는 산양 </li>
        <li class="list-group-item"><label><b>이메일</b></label> bausa@gmail.com </li>
        <li class="list-group-item"><label><b>전화번호</b></label> 010-5454-5454 </li>
      </ul>
    </div>
  </div>
  </template>
  
  <style>
  .profile {
    display: flex;
  }
.profilephoto img{
  width: 150px;
  height: 150px;
}
.list-group-item {
  display: flex;
  justify-content: space-between;
}
  </style>
  