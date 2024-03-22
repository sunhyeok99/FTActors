import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import ProfileView from "../views/profileview/ProfileView.vue";
import BoardView from "../views/boardview/BoardView.vue";
import MontageListView from "../views/montageview/MontageView.vue";
import MontageMainView from "../views/montageview/MontageMain.vue";
import LoginView from "../views/memberview/LoginView.vue";
import LogoutView from "../views/memberview/LogoutView.vue";
import JoinView from "../views/memberview/JoinView.vue";
import MypageView from "@/views/memberview/MypageView.vue";
import BoardDetail from "@/views/boardview/BoardDetail.vue";
import BoardCreate from "@/views/boardview/BoardCreate.vue";
import BoardUpdate from "@/views/boardview/BoardUpdate.vue";
import MontageDetail from "@/views/montageview/MontageDetail.vue";
import MontageCreate from "@/views/montageview/MontageCreate.vue";
import MontageUpdate from "@/views/montageview/MontageUpdate.vue";
import ProfileDetail from "@/views/profileview/ProfileDetail.vue";
import ProfileCreate from "@/views/profileview/ProfileCreate.vue";
import ProfileUpdate from "@/views/profileview/ProfileUpdate.vue";
import BlacklistView from "@/views/adminview/BlacklistView.vue";
import ReportView from "@/views/adminview/ReportView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/profile",
      name: "profile",
      component: ProfileView,
    },
    {
      path: "/board",
      name: "board",
      component: BoardView,
    },
    {
      path: "/montagelist",
      name: "montagelist",
      component: MontageListView,
    },
    {
      path: "/montagemain",
      name: "montagemain",
      component: MontageMainView,
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
    },
    {
      path: "/join",
      name: "join",
      component: JoinView,
    },
    {
      path: "/mypage",
      name: "mypage",
      component: MypageView,
    },
    {
      path: "/boarddetail/:id",
      name: "boardDetail",
      component: BoardDetail,
      props: true,
    },
    {
      path: "/boardcreate",
      name: "boardCreate",
      component: BoardCreate,
    },
    {
      path: "/boardupdate",
      name: "boardUpdate",
      component: BoardUpdate,
      props: true,
    },

    {
      path: "/montagedetail/:id",
      name: "montageDetail",
      component: MontageDetail,
      props:true,
    },
    {
      path: "/montagecreate",
      name: "montageCreate",
      component: MontageCreate,
    },
    {
      path: "/montageupdate",
      name: "montageUpdate",
      component: MontageUpdate,
    },
    {
      path: "/profiledetail/:id",
      name: "profileDetail",
      component: ProfileDetail,
      props: true,
    },

    {
      path: "/profilecreate",
      name: "profileCreate",
      component: ProfileCreate,
    },
    {
      path: "/profileupdate",
      name: "profileUpdate",
      component: ProfileUpdate,
    },
    {
      path: "/blacklist",
      name: "blacklist",
      component: BlacklistView,
    },
    {
      path: "/report",
      name: "report",
      component: ReportView,
    },
  ],
});

export default router;
