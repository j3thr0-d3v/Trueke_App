<script setup>
import useAuth from "@/stores/auth";
import { onMounted, ref } from "vue";

const authStore = useAuth();

const worker = ref({
  username: String,
  name: String,
  surname: String,
  email: String,
  dni: String,
  phone: String,
  avatarUrl: String,
});

onMounted(async () => {
  await authStore.getUser();
  worker.value = {
    username: authStore.user.username,
    name: authStore.user.name,
    surname: authStore.user.surname,
    email: authStore.user.email,
    dni: authStore.user.dni,
    phone: authStore.user.phone,
    career: authStore.user.career,
    avatarUrl: `${authStore.baseURL}/download/${authStore.user.avatarUrl}`,
    associationLogo: `${authStore.baseURL}/download/${authStore.user.association.logo}`,
  };
});
</script>

<template>
  <div class="container-fluid h-100 bg-white rounded-5 overflow-hidden">
    <div class="card row h-25 bg-dark justify-content-center p-2">
      <img
        class="card-img object-fit-scale h-100 rounded-5"
        :src="worker.avatarUrl"
        alt="profile-pic"
      />
      <div class="card-img-overlay">
        <router-link>
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
        </router-link>
      </div>
    </div>
    <div class="text-center mb-3">
      <span class="fw-bold fs-4">{{ worker.name }} {{ worker.surname }},</span>
      <span class="fw-bold"> ( {{ worker.username }} )</span>
      <hr />
      <h5 class="text-warning">Experiencia Laboral</h5>
      <span class="fst-italic">"{{ worker.career }}"</span>
      <hr />
      <h5 class="text-warning">Datos</h5>
      <div class="text-start d-inline-block border">
        <span
          ><i class="bi bi-telephone-fill"></i> Telefono :
          {{ worker.phone }}</span
        ><br />
        <span><i class="bi bi-envelope"></i> email : {{ worker.email }}</span
        ><br />
        <span><i class="bi bi-person-vcard"></i> dni: {{ worker.dni }}</span>
      </div>
      <hr />
      <div>
        <img class="border border-3 border-dark rounded-5" width="35%" :src="worker.associationLogo" alt="logo">
      </div>
      <div class="container-fluid w-50">
        <div class="row my-3">
          <router-link class="col btn btn-primary rounded-pill fw-bold" to="">
            Editar Perfil
          </router-link>
        </div>
        <div class="row">
          <button class="col btn btn-danger rounded-pill fw-bold" @click="">
            Borrar Cuenta
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}
.font-lemon {
  font-family: "LemonMilkBold";
}
.font-size {
  font-size: 1em;
}
svg {
  fill: #ffc107;
}
svg:hover {
  fill: black;
  background-color: #ffc107 !important;
}
</style>
