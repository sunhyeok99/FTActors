<script>
/*
Vue.js 컴포넌트의 JavaScript 로직을 작성하는 데 사용됩니다. 
이 요소 안에는 해당 컴포넌트의 데이터, 메서드, 계산된 속성 등이 정의됩니다.
*/

import { ffmpeg } from "@ffmpeg/ffmpeg";
import { defineComponent, ref } from "vue";
import { userDropzone } from 'vue3-dropzone'; 

import { fetchFile, toBlobURL } from '@ffmpeg/util';


const edit = () => {
    const [loaded, setLoaded] = useState(false);
    const ffmpegRef = useRef(new FFmpeg());
    const videoRef = useRef(null);
    const messageRef = useRef(null);

    const load = async () => {
        const baseURL = 'https://unpkg.com/@ffmpeg/core@0.12.6/dist/esm'
        const ffmpeg = ffmpegRef.current;
        ffmpeg.on('log', ({ message }) => {
            messageRef.current.innerHTML = message;
            console.log(message);
        });
        // toBlobURL is used to bypass CORS issue, urls with the same
        // domain can be used directly.
        await ffmpeg.load({
            coreURL: await toBlobURL(`${baseURL}/ffmpeg-core.js`, 'text/javascript'),
            wasmURL: await toBlobURL(`${baseURL}/ffmpeg-core.wasm`, 'application/wasm'),
        });
        setLoaded(true);
    }

    const transcode = async () => {
        const ffmpeg = ffmpegRef.current;
        await ffmpeg.writeFile('input.webm', await fetchFile('https://raw.githubusercontent.com/ffmpegwasm/testdata/master/Big_Buck_Bunny_180_10s.webm'));
        await ffmpeg.exec(['-i', 'input.webm', 'output.mp4']);
        const data = await ffmpeg.readFile('output.mp4');
        videoRef.current.src =
            URL.createObjectURL(new Blob([data.buffer], {type: 'video/mp4'}));
    }
}
</script>

<template>
    <div>
      <template v-if="loaded">
        <video ref="videoRef" controls></video><br/>
        <button @click="transcode">Transcode webm to mp4</button>
        <p ref="messageRef"></p>
        <p>개발자 도구를 열어 로그를 확인하세요 (Ctrl+Shift+I)</p>
      </template>
      <template v-else>
        <button @click="load">ffmpeg-core (~31 MB) 로드하기</button>
      </template>
    </div>
  </template>
<style>
</style>