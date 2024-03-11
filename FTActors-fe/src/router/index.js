import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProfileView from '../views/ProfileView.vue'
import BoardView from '../views/BoardView.vue'
import MontageView from '../views/MontageView.vue'
import AboutView from '../views/AboutView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import JoinView from '../views/JoinView.vue'
import MypageView from '@/views/MypageView.vue'
import BoardDetail from '@/views/BoardDetail.vue'
import MontageDetail from '@/views/MontageDetail.vue'
import ProfileDetail from '@/views/ProfileDetail.vue'
import BoardCreate from '@/views/BoardCreate.vue'
import BoardUpdate from '@/views/BoardUpdate.vue'
import MontageCreate from '@/views/MontageCreate.vue'
import MontageUpdate from '@/views/MontageUpdate.vue'
import ProfileCreate from '@/views/ProfileCreate.vue'
import ProfileUpdate from '@/views/ProfileUpdate.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfileView
    },
    {
      path: '/board',
      name: 'board',
      component: BoardView
    },
    {
      path: '/montage',
      name: 'montage',
      component: MontageView
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/logout',
      name: 'logout',
      component: LogoutView
    },
    {
      path: '/join',
      name: 'join',
      component: JoinView
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MypageView
    },
    {
      path: '/boarddetail',
      name: 'boardDetail',
      component: BoardDetail
    },
    {
      path: '/boardcreate',
      name: 'boardCreate',
      component: BoardCreate
    },
    {
      path: '/boardupdate',
      name: 'boardUpdate',
      component: BoardUpdate
    },

    {
      path: '/montagedetail',
      name: 'montageDetail',
      component: MontageDetail
    }
    ,
    {
      path: '/montagecreate',
      name: 'montageCreate',
      component: MontageCreate
    },
    {
      path: '/montageupdate',
      name: 'montageUpdate',
      component: MontageUpdate
    },
    {
      path: '/profiledetail',
      name: 'profileDetail',
      component: ProfileDetail
    },
    
    {
      path: '/profilecreate',
      name: 'profileCreate',
      component: ProfileCreate
    },
    {
      path: '/profileupdate',
      name: 'profileUpdate',
      component: ProfileUpdate
    },
  ]
})

export default router
