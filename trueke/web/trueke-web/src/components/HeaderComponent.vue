<script setup>
import router from '@/router';
import useAuth from '@/stores/auth';
import { ref } from 'vue';

const store = useAuth();

let username = ref(store.user.username)

const logout = ()=>{
  store.user.token = null;
  router.push({name: 'login'})
}
</script>

<template>
  <nav class="navbar bg-dark p-0">
    <div class="row container-fluid">
      <div class="col-6 h-100 ps-5">
        <img class="img-fluid h-10 m-0 p-0" src="../assets/g4lKbU01.svg" />
        <span class="lemon-font text-light fs-1 align-middle">Trueke App</span>
      </div>
      <div class="col-6 text-end container-fluid h-100">
        <img
          src="../assets/picsvg_download.svg"
          alt="User icon"
          class="bg-warning rounded-circle p-2 m-3"
        />
        <div class="btn-group" role="group">
          <button
            type="button"
            class="btn btn-outline-warning btn-lg lemon-font"
          >
            {{username}}
          </button>
          <button
            type="button"
            class="btn btn-outline-danger btn-lg lemon-font border-warning"
            @click="logout"
          >
            <svg
              class="h-100 w-100 m-0 p-0"
              height="24"
              version="1.1"
              id="Capa_1"
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
              viewBox="0 0 384.971 384.971"
              xml:space="preserve"
            >
              <g>
                <g id="Sign_Out">
                  <path
                    d="M180.455,360.91H24.061V24.061h156.394c6.641,0,12.03-5.39,12.03-12.03s-5.39-12.03-12.03-12.03H12.03
			C5.39,0.001,0,5.39,0,12.031V372.94c0,6.641,5.39,12.03,12.03,12.03h168.424c6.641,0,12.03-5.39,12.03-12.03
			C192.485,366.299,187.095,360.91,180.455,360.91z"
                  />
                  <path
                    d="M381.481,184.088l-83.009-84.2c-4.704-4.752-12.319-4.74-17.011,0c-4.704,4.74-4.704,12.439,0,17.179l62.558,63.46H96.279
			c-6.641,0-12.03,5.438-12.03,12.151c0,6.713,5.39,12.151,12.03,12.151h247.74l-62.558,63.46c-4.704,4.752-4.704,12.439,0,17.179
			c4.704,4.752,12.319,4.752,17.011,0l82.997-84.2C386.113,196.588,386.161,188.756,381.481,184.088z"
                  />
                </g>
                <g></g>
                <g></g>
                <g></g>
                <g></g>
                <g></g>
                <g></g>
              </g>
            </svg>
          </button>
        </div>
      </div>
    </div>
  </nav>
</template>

<style scoped>
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}
.lemon-font {
  font-family: "LemonMilkBold";
}
svg {
  fill: #dc3545;
}
svg:hover {
  fill: white;
}
</style>
