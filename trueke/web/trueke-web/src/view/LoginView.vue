<script setup>
import { ref } from "vue";
import useAuth from "@/stores/auth";
import router from "@/router";

const store = useAuth();

let username = ref("");
let password = ref("");
let feedback = ref("");

// const loginUser = async () => {
//   feedback.value = "Sending info...";
//   const success = await store.login(username.value, password.value);
//   if (!success) {
//     feedback.value = "El Login ha fallado";
//     router.push("/auth/login")
//   } else {
//     if(store.roles.split(",").includes("COLLABORATOR")){
//       router.push("/")
//     }else{
//       router.push("/association")
//     }
//   }
// };
const loginUser = async () => {
  try {
    feedback.value = "Sending info...";
    const success = await store.login(username.value, password.value);
    if (!success) {
      throw new Error("Login failed"); // Throw a specific error for better handling
    }

    const userRoles = store.roles.split(',');
    if (userRoles.includes('COLLABORATOR')) {
      router.push('/');
    } else {
      router.push('/association');
    }
  } catch (error) {
    feedback.value = "Login failed: " + error.message; // Provide informative feedback
    console.error('Login error:', error); // Log the error for debugging
  }
};
</script>

<template>
  <div class="vh-100 container-fluid bg-dark">
    <div class="vh-25 d-flex justify-content-center align-items-center">
      <span id="title" class="text-warning border border-3 border-warning rounded-pill px-5">Trueke APP</span>
    </div>
    <div id="form" class="container justify-content-center">
      <div class="container w-25">
        <form class="border-warning border-3 border p-5 text-center rounded-5">
          <div class="mb-3">
            <label for="username" class="form-label text-warning fw-bold">Email/Username</label>
            <input
              type="text"
              class="form-control"
              id="username"
              v-model="username"
            />
            <div id="userbaneHelp" class="form-text text-white">
              No compartiremos tus datos con nadie.
            </div>
          </div>
          <div class="mb-3 ">
            <label for="password" class="form-label text-warning fw-bold">Contraseña</label>
            <input
              type="password"
              class="form-control"
              id="password"
              v-model="password"
            />
          </div>
          <button
            @click.prevent="loginUser"
            type="submit"
            class="btn btn-primary mt-2 px-5 rounded-pill "
          >
            Entrar
          </button>
          <p class="text-danger">{{ feedback }}</p>
          
        </form>
        <router-link class="text-center d-flex justify-content-center mt-3 text-decoration-none" to="/auth/register">¿Quieres registrate? Pulsa aquí</router-link>
        <router-link class="text-center d-flex justify-content-center mt-3 text-decoration-none" to="/auth/register/association">¿Tienes una asociación? Únete pinchando aquí</router-link>
        
      </div>
    </div>
  </div>
</template>

<style scoped>
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}
#title{
  font-family: 'LemonMilkBold';
  font-size: 5em
}
form{
  /* background-color: rgb(187, 173, 13); */
}
</style>
