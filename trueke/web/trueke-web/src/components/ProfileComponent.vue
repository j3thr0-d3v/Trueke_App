<script setup>
import useAuth from '@/stores/auth';
import { onBeforeMount, onMounted, ref } from 'vue';

const store = useAuth();

onMounted(async ()=>{
  await store.getUser()
  username.value = store.user.username
  name.value = store.user.name;
  age.value = store.user.age;
  motivation.value = store.user.motivation
  skills.value = store.user.skills
})

let username = ref('')
let name = ref('')
let age = ref('')
let motivation = ref('')
let skills = ref('')

</script>

<template>
  <div class="container-fluid h-100 bg-white rounded-5 overflow-hidden">
    <div class="card row h-25 bg-dark justify-content-center p-2">
      <img
        class="card-img object-fit-scale h-100 rounded-5"
        src="https://estaticos-cdn.prensaiberica.es/clip/fa5f055b-ad24-49a2-8cff-f3abe1c23861_16-9-aspect-ratio_default_0.jpg"
        alt="profile-pic"
      />
      <div class="card-img-overlay ">
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
    <div class="text-center">
      <span class="fw-bold fs-4">{{ name }}, {{ age }}</span>
      <span class="fw-bold"> ( {{ username }} )</span>
      <hr>
      <h5 class="text-warning">Motivación</h5>
      <span class="fst-italic">"{{ motivation }}"</span>
      <hr>
      <h5 class="text-warning">Habilidades</h5>
      <span class="fst-italic">"{{ skills }}"</span>
      
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
</style>
