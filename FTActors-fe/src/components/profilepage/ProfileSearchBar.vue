<template>
    <div class="column search-bar">
        <!-- 프로필 정렬 드롭다운 -->
        <!-- <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              {{ selectedSortOption }}
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#" @click="updateSortOption(1)">최신순</a></li>
              <li><a class="dropdown-item" href="#" @click="updateSortOption(2)">오래된순</a></li> -->
              <!-- <li><a class="dropdown-item" href="#">좋아요순</a></li> -->
            <!-- </ul>
          </div> -->

        
        
          <!-- 검색 창 -->
        <form class="d-flex" role="search" @submit.prevent="search">
          <!-- 검색 대상 드롭다운 -->
          <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              {{ selectedField }}
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#" @click="selectedField = '자기소개'">자기소개</a></li>
              <li><a class="dropdown-item" href="#" @click="selectedField = '예명'">예명</a></li>
              <!-- <li><a class="dropdown-item" href="#" @click="selectedField = '이름'">이름(실명)</a></li> -->
            </ul>
          </div>
          &nbsp;&nbsp;
            <input class="form-control me-2" id="search" type="search" placeholder="키워드를 입력해 주세요" aria-label="Search" v-model="keyword">
            <button class="btn btn-outline-success" type="submit">검색</button>
          </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
// import { profileApi } from '@/util/axios.js'; // axios 가져오기
import { profileApi } from '../../util/axios.js';


const selectedSortOption = ref('프로필 검색'); // 기본값은 '프로필 검색'
const selectedField = ref('검색 대상'); // 기본값은 '이름(실명)'
const keyword = ref('');

const updateSortOption = (option) => {
  selectedSortOption.value = option;
};
const updateSearchOption = (option) => {
  selectedField.value = option;
};

const search = () => {
  // 선택한 필드와 키워드를 이용하여 검색을 수행할 수 있도록 작성
  console.log('선택한 필드:', selectedField.value);
  console.log('키워드:', keyword.value); //v-model="keyword" 에 있음

  // 검색어를 백엔드로 보내고 데이터를 받아오는 함수 호출
  searchContent(keyword.value);
  // searchByName(findName.value);
  searchByStageName(keyword.value);
};

// 프로필 목록을 저장할 변수
const profiles = ref([]);

// 프로필 목록 가져오기
const fetchProfiles = () => {
  profileApi.getAllProfileList()
    .then(response => {
      profiles.value = response.data.data; // 프로필 목록 업데이트
      console.log("프로필 데이터를 불러옵니다");
      console.log(profiles.value);
      //이거를 Array에 넣어야겠쬬
      //map 돌려서 구현하면 댐

    })
    .catch(error => {
      console.error('프로필 목록 가져오기 실패:', error);
    });
};

// 내용(자기소개) 찾기
const searchContent = (keywords) => {
  // profileApi를 사용하여 백엔드로 요청을 보냄
  profileApi.searchContent(keywords)
    .then(response => {
      // 백엔드로부터 받은 데이터를 처리하는 코드 작성
      console.log('검색 결과:', response.data.data);
    })
    .catch(error => {
      // 요청 실패 시 에러 처리
      console.error('검색 요청 실패:', error);
    });
};

// // 이름(실명) 찾기
// const searchByName = (findName) => {
//   // profileApi를 사용하여 백엔드로 요청을 보냄
//   profileApi.searchContent(findName)
//     .then(response => {
//       // 백엔드로부터 받은 데이터를 처리하는 코드 작성
//       console.log('검색 결과:', response.data.data);
//     })
//     .catch(error => {
//       // 요청 실패 시 에러 처리
//       console.error('검색 요청 실패:', error);
//     });
// };

// 예명 찾기
const searchByStageName = (keywords) => {
  // profileApi를 사용하여 백엔드로 요청을 보냄
  profileApi.searchByStageName(keywords)
    .then(response => {
      // 백엔드로부터 받은 데이터를 처리하는 코드 작성
      console.log('검색 결과:', response.data.data);
    })
    .catch(error => {
      // 요청 실패 시 에러 처리
      console.error('검색 요청 실패:', error);
    });
};

// 컴포넌트가 마운트될 때 프로필 목록 가져오기
onMounted(fetchProfiles);

// export { profiles };
</script>

<style>
.search-bar{
    display: flex;
    justify-content: space-between;
    align-items: center;
}
#search{
    width: auto;
}
</style>