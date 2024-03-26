<script>
/*
Vue.js 컴포넌트의 JavaScript 로직을 작성하는 데 사용됩니다. 
이 요소 안에는 해당 컴포넌트의 데이터, 메서드, 계산된 속성 등이 정의됩니다.
*/

import { ref, reactive } from 'vue';
import { createFFmpeg } from "@ffmpeg/ffmpeg";

import { FFmpeg } from '@ffmpeg/ffmpeg'
import { toBlobURL, fetchFile } from '@ffmpeg/util'

const ffmpeg = new FFmpeg()
const message = ref('Click Start to Transcode')
const video = ref('')
const baseURL = 'https://unpkg.com/@ffmpeg/core-mt@0.12.6/dist/esm'
const url = ref('')

async function handleFileChange(event) {
  const file = event.target.files[0] 
  // input 태그로 동영상 받아온다.
  if (!file) return

  message.value = 'Loading ffmpeg-core.js'
  await ffmpeg.load({
    coreURL: await toBlobURL(`${baseURL}/ffmpeg-core.js`, 'text/javascript'),
    wasmURL: await toBlobURL(`${baseURL}/ffmpeg-core.wasm`, 'application/wasm'),
    workerURL: await toBlobURL(`${baseURL}/ffmpeg-core.worker.js`, 'text/javascript')
  })
  // ffmpeg 사용하기 위해서는 ffmpeg.load 실행 필요
  message.value = 'FFmpeg is loaded.'

  await ffmpeg.writeFile('test.mp4', await fetchFile(file))
  // 받아온 파일을 'test.mp4' 이름으로 작업하기 위해 생성 (writeFile)

  const data = await ffmpeg.readFile('test.mp4')
  // write가 잘 됐는지 확인 (readFile) 
  
  video.value = URL.createObjectURL(new Blob([data.buffer], { type: file.type }))
  // video에 URL값 저장하여 video태그 src에 값 저장하여 동영상 확인 
}

// 인코딩 실행 버튼 클릭시 실행하는 transcode 함수
async function transcode() {
  if (!video.value) {
    message.value = 'No video loaded'
    return
  }

  message.value = 'Start transcoding'
  ffmpeg.on('log', ({ message: msg }) => {
    console.log(msg)
    message.value = msg
  })
// 진행 로그를 message 변수에 저장
  
  await ffmpeg.exec([
    '-i',
    'test.mp4',
    '-codec',
    'copy',
    '-start_number',
    '0',
    '-hls_time',
    '60',
    '-hls_list_size',
    '0',
    '-f',
    'hls',
    'output.m3u8'
  ])
  // 인코딩 실행
  
  message.value = 'Transcoding completed'
  const m3u8Data = await ffmpeg.readFile('output.m3u8')
  // 인코딩이 'output.m3u8'에 저장이 잘 됐는지 확인
  let index = 0
  let fileExists = true

  while (fileExists) {
    const fileName = `output${index}.ts`

    try {
      const tsFiles1 = await ffmpeg.readFile(`output${index}.ts`)
      console.log(fileName)
      console.log(tsFiles1)
      index++
    } catch (err) {
      fileExists = false
    }
  }
  console.log('end')
  console.log(m3u8Data)
  url.value = URL.createObjectURL(new Blob([m3u8Data.buffer], { type: 'any' }))
  console.log(url.value)
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