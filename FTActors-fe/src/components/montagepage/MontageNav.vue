<template>
  <nav class="navbar navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" id="toHome" @click="goToHomeview">배우는 사람</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar"
        aria-controls="offcanvasDarkNavbar" aria-label="Toggle navigation">
        <img src="@/assets/icons/Slate.png" alt="" id="slateicon">
      </button>
      <div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar"
        aria-labelledby="offcanvasDarkNavbarLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel"></h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas"
            aria-label="Close"></button>
        </div>
        <div class="offcanvas-body" id="offcanvas-body">
          <MontageFeed />
          <!-- <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                aria-expanded="false">
                Dropdown
              </a>
              <ul class="dropdown-menu dropdown-menu-dark">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li>
                  <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
              </ul>
            </li>
          </ul> -->
          <form class="d-flex mt-3" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </div>
  </nav>
</template>
<script setup>

import MontageFeed from '@/components/montagepage/MontageFeed.vue'
import { RouterLink, RouterView, useRouter } from 'vue-router'
const router = useRouter();
const goToHomeview = () => {
  router.push({ name: 'home' }).then(() => {
    window.scrollTo({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  });
};

</script>

<style scoped>
.offcanvas-lg {
  width: 80%;
}

#offcanvas-body{
  background-color: rgb(33, 37, 41);
}

#offcanvasDarkNavbarLabel img {
  width: 50px;
  height: 50px;
}

#toHome {
  cursor: pointer;
}

#slateicon{
  width: 30px;
  height: 30px;
  filter: invert(100%);
}
.offcanvas-header {
  background-image: linear-gradient(45deg, rgb(33, 37, 41) 25%, #ADB5BD 25%, #ADB5BD 50%, rgb(33, 37, 41) 50%, rgb(33, 37, 41) 75%, #ADB5BD 75%, #ADB5BD);
  background-size: 100px 100px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}


</style>