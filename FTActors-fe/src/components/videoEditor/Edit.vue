<template>
  <div>
    <!-- 비디오 플레이어 -->
    <video ref="videoRef" @ended="playNextVideo" :src="videoSource" :volume="nowPlayingVideo.clipVolume / 200" autoplay />
    <!-- BGM -->
    <audio ref="bgmRef" :src="studioDetailInfo.bgmUrl" :volume="studioBGMVolume / 200" />

    <!-- 사용되지 않은 클립 리스트 -->
    <div v-if="notUsedClipList.length">
      <div v-for="clip in notUsedClipList" :key="clip.clipId">
        <button @click="selectVideo(clip.clipId)">선택</button>
        <span>{{ clip.clipTitle }}</span>
      </div>
    </div>

    <!-- 사용된 클립 리스트 -->
    <div v-if="usedClipList.length">
      <div v-for="clip in usedClipList" :key="clip.clipId">
        <button @click="unselectClip(clip.clipId)">취소</button>
        <span>{{ clip.clipTitle }}</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted} from 'vue';

const props = defineProps({
  videoFile : Array,
  videoVolume: Number
})


const studioDetailInfo = reactive({});
const studioBGMVolume = ref(100);
const usedClipList = ref([]);
const notUsedClipList = ref([]);
const nowPlayingVideo = ref({
  clipId: -1,
  clipTitle: '',
  clipOwner: '',
  clipLength: -1,
  clipThumbnail: '',
  clipUrl: '',
  clipOrder: -1,
  clipVolume: 100,
  clipContent: '',
});

const videoRef = ref(null);
const bgmRef = ref(null);
const playingIdx = ref(0);


// 초기 설정
const initSetting = async () => {
    // 스튜디오 상세 정보 받아오기(사용한 영상, 사용하지 않은 영상 리스트를 DB에 저장해 불러옴)
    // const res = await studioDetail(studioId);
    // if (res.status === httpStatusCode.OK) {
    //     const studioDetailInfo = reactive({ ...res.data });
    //     setStudioDetailInfo(studioDetailInfo);

      // 사용 영상, 사용하지 않은 영상 리스트 출력
    //const tempClipList = res.data.clipInfoList;
    
    const tempClipList = props.videoFile;
    const clipList = tempClipList.sort((clipA, clipB) => clipA.clipOrder - clipB.clipOrder);
    const initialUsedVideo = [];
    const nonInitialUsedVideo = [];

    // 사용, 사용하지 않은 영상 구분하는 부분
    for (let i = 0; i < clipList.length; i++) {
        if (clipList[i].clipOrder === -1) {
            nonInitialUsedVideo.push(clipList[i]);
        } else {
            initialUsedVideo.push(clipList[i]);
        }
    }

    // 구분 후 불러온 정보로 초기화
    usedClipList.value = initialUsedVideo;
    notUsedClipList.value = nonInitialUsedVideo;
    //}
};

const videoSource = computed(()=>{
  if(!props.videoFile) return '';
  // console.log(props.videoFile);
  // console.log("COMPUTED 실행"); // 얘가 동영상이 다 끝나면 계속 실행되는 것 같아..
  // nowPlayingVideo.value.clipTitle = props.videoFile.name;
  // nowPlayingVideo.value.clipUrl = URL.createObjectURL(props.videoFile)
  // return nowPlayingVideo.value.clipUrl;
})

// 비디오 재생
const playVideo = () => {
  // 비디오가 있으면 재생
  if (videoRef.value) {
    videoRef.value.play();
  }
};

// 비디오 정지
const stopVideo = () => {
  // 비디오가 있으면 정지
  if (videoRef.value) {
    videoRef.value.pause();
  }
};

// 다음 비디오 재생
const playNextVideo = () => {
  if (playingIdx.value < usedClipList.value.length - 1) {
    playingIdx.value++;
    nowPlayingVideo.value = usedClipList.value[playingIdx.value];
    playVideo();
  } else {
    playingIdx.value = 0;
    nowPlayingVideo.value = usedClipList.value[playingIdx.value];
    stopVideo();
  }
};

// 클립 선택
const selectVideo = (clipId) => {
  const selectedClipIndex = notUsedClipList.value.findIndex(clip => clip.clipId === clipId);
  const selectedClip = notUsedClipList.value.splice(selectedClipIndex, 1)[0];
  usedClipList.value.push(selectedClip);
  playVideo();
};

// 클립 취소
const unselectClip = (clipId) => {
  const unselectedClipIndex = usedClipList.value.findIndex(clip => clip.clipId === clipId);
  const unselectedClip = usedClipList.value.splice(unselectedClipIndex, 1)[0];
  notUsedClipList.value.push(unselectedClip);
  stopVideo();
};


onMounted(initSetting);
</script>

<style>
/* 스타일 코드 여기에 작성 */
</style>
