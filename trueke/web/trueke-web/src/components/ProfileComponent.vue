<script setup>
import useAuth from "@/stores/auth";
import { onMounted, ref } from "vue";

const store = useAuth();

onMounted(async () => {
  await store.getUser();
  username.value = store.user.username;
  name.value = store.user.name;
  age.value = store.user.age;
  motivation.value = store.user.motivation;
  skills.value = store.user.skills;
  avatarUrl.value = store.user.avatarUrl== null ? "https://t3.ftcdn.net/jpg/02/48/42/64/360_F_248426448_NVKLywWqArG2ADUxDq6QprtIzsF82dMF.jpg" : `${store.baseURL}/download/${store.user.avatarUrl}`;
});

let username = ref("");
let name = ref("");
let age = ref("");
let motivation = ref("");
let skills = ref("");
let avatarUrl = ref("");
</script>

<template>
  <div class="container-fluid h-100 bg-white rounded-5 overflow-hidden">
    <div class="card row h-25 bg-dark justify-content-center p-2">
      <img
        class="card-img object-fit-scale h-100 rounded-5"
        :src="avatarUrl"
        alt="profile-pic"
      />
      <div class="card-img-overlay">
        <svg
          class="border border-solid border-warning btn rounded-circle p-1 card-text position-absolute bottom-0 end-0 me-3 mb-3"
          xmlns="http://www.w3.org/2000/svg"
          height="30px"
          viewBox="0 0 24 24"
          width="30px"
          fill="#000000"
        >
          <path d="M0 0h24v24H0V0z" fill="none" />
          <path
            d="M18 20H4V6h9V4H4c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2v-9h-2v9zm-7.79-3.17l-1.96-2.36L5.5 18h11l-3.54-4.71zM20 4V1h-2v3h-3c.01.01 0 2 0 2h3v2.99c.01.01 2 0 2 0V6h3V4h-3z"
          />
        </svg>
      </div>
    </div>
    <div class="text-center mb-3">
      <span class="fw-bold fs-4">{{ name }}, {{ age }}</span>
      <span class="fw-bold"> ( {{ username }} )</span>
      <hr />
      <h5 class="text-warning">Motivación</h5>
      <span class="fst-italic">"{{ motivation }}"</span>
      <hr />
      <h5 class="text-warning">Habilidades</h5>
      <span class="fst-italic">"{{ skills }}"</span>
      <hr>
    </div>
    <h5 class="text-center text-warning">Mis Eventos</h5>
    <div class="list-group  text-center">
      <a href="#" class="link list-group-item list-group-item-action mt-2 bg-warning rounded-pill"
        ><span class="lemon">Mis Colaboraciones</span>
        <span class="lemon ms-5 badge text-bg-light rounded-pill counter">14</span></a
      >
      <a href="#" class="link list-group-item list-group-item-action mt-2 bg-warning rounded-pill"
        ><span class="lemon">Eventos Por Comenzar</span>
        <span class="lemon ms-5 badge text-bg-light rounded-pill counter">6</span></a
      >
      <a href="#" class="link list-group-item list-group-item-action mt-2 bg-warning rounded-pill"
        ><span class="lemon">Eventos finalizados</span>
        <span class="lemon ms-5 badge text-bg-light rounded-pill counter">8</span></a
      >
    </div>
    <div class="container-fluid w-50">
      <div class="row my-3">
        <button class="col btn btn-primary rounded-pill fw-bold">Editar Perfil</button>
      </div>
      <div class="row">     
        <button class="col btn btn-danger rounded-pill fw-bold">Borrar Cuenta</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
svg {
  fill: #ffc107;
}
svg:hover {
  fill: black;
  background-color: #ffc107 !important;
}
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}
.lemon{
  font-family: "LemonMilkBold";
}
.link:hover{
  background-color: rgb(163, 53, 34) !important;
  color: #ffffff;
  text-shadow: 1px 1px 2px black;
}
.counter{
 text-shadow:  none !important;
}
</style>
