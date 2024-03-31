import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  // server: {
  //   proxy: {
  //     '/api': {
  //       target: 'http://localhost:8080', // 백엔드 서버 주소
  //       changeOrigin: true, // 호스트 헤더를 target URL로 변경
  //       secure: false, // HTTPS 사용 시 true로 설정
  //     },
  //   },
  // },
  
})

