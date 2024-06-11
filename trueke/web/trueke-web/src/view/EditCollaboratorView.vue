<script setup>
import router from "@/router";
import useAuth from "@/stores/auth";
import { ref } from "vue";

const authStore = useAuth();

const name = ref(authStore.user.name);
const surname = ref(authStore.user.surname);
const skills = ref(authStore.user.skills);
const motivation = ref(authStore.user.motivation);
const imgProfilePreview = ref(`${authStore.baseURL}/download/${authStore.user.avatarUrl}`);
const imgProfile = ref(`${authStore.baseURL}/download/${authStore.user.avatarUrl}`);

const pwdChangeCheck = ref(false);

const oldPassword = ref('');
const newPassword = ref('');
const verifyPassword = ref('');

const createUpdateRequest = () => {
  let updateRequest = {
        name: name.value,
        surname: surname.value,
        skills: skills.value,
        motivation: motivation.value
    }
    authStore.editCollaborator(updateRequest, imgProfile.value)
    if(pwdChangeCheck.value){
      changePassword()
      router.push("/auth/login")
    }
    router.push("/")
}

const changePassword = () => {
  let changePwdRequest = {
    oldPassword: oldPassword.value,
    newPassword: newPassword.value,
    verifyPassword: verifyPassword.value
  }
  authStore.changePassword(changePwdRequest)
}

const handleFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      imgProfilePreview.value = e.target.result;
      imgProfile.value = file;
    };
    reader.readAsDataURL(file);
  }
};


</script>

<template>
  <div class="container-fluid bg-dark h-100 vh-100 p-4">
    <div class="vh-5 mb-4 d-flex justify-content-center align-items-center">
      <span
        class="headline text-warning border border-3 border-warning rounded-pill px-5"
        >Editar Perfil</span
      >
    </div>
    <div class="container w-50">
      <form
        class="needs-validation p-4 border-warning border-3 border rounded-5"
      >
        <div class="row mb-3">
          <div class="col">
            <div class="h-10 text-center">
              <img
                class="rounded-4 h-100 border border-3 border-warning"
                :src="imgProfilePreview"
                alt="uploaded photo"
              />
            </div>
            <div class="text-center mx-auto mt-3 w-50">
              <label for="photo" class="fw-bold text-white"
                >Seleciona foto de perfil</label
              >
              <input
                id="photo"
                class="form-control"
                @change="handleFileChange"
                type="file"
                accept="image/*"
              />
            </div>
          </div>
        </div>
        <div class="row mt-3">
          <div class="col">
            <div class="input-group">
              <span class="input-group-text fw-bold">Nombre</span>
              <input
                v-model="name"
                type="text"
                class="form-control"
                placeholder="ej... Miguel Angel"
                aria-label="Nombre"
              />
            </div>
          </div>
          <div class="col">
            <div class="input-group">
              <span class="input-group-text fw-bold">Apellidos</span>
              <input
                v-model="surname"
                type="text"
                class="form-control"
                placeholder="ej... Pérez Rodríguez"
                aria-label="Apellidos"
              />
            </div>
          </div>
        </div>
        <div class="row mt-3 w-100 mx-auto">
          <h4 class="text-white text-center mt-3">Motivaciones</h4>
          <textarea
            v-model="motivation"
            class="form-control w-100"
            placeholder="¿Por qué deseas unirte?"
          ></textarea>
        </div>
        <div class="row mt-3 w-100 mx-auto">
          <h4 class="text-white text-center mt-3">Habilidades</h4>
          <textarea
            v-model="skills"
            class="form-control w-100"
            placeholder="¿Qué habilidades o conocimientos posees?"
          ></textarea>
        </div>
        <div class="form-check text-center d-flex justify-content-center mt-3">
          <input
            class="form-check-input"
            type="checkbox"
            value=""
            id="flexCheckDefault"
            v-model="pwdChangeCheck"
          />
          <label
            class="ms-2 form-check-label fw-bold text-white"
            for="flexCheckDefault"
          >
            Cambiar Contraseña
          </label>
        </div>
        <div v-if="pwdChangeCheck" >
          <h4 class="text-white text-center mt-3">Introduzca su Antigua Contraseña</h4>
          <div class="row mt-3">
            <div class="col">
              <div class="input-group">
                <span class="input-group-text fw-bold">Contraseña</span>
                <input
                  v-model="oldPassword"
                  type="password"
                  class="form-control"
                />
              </div>
            </div>
          </div>
          <h4 class="text-white text-center mt-3">Nueva Contraseña</h4>
          <div class="row mt-3">
            <div class="col">
              <div class="input-group">
                <span class="input-group-text fw-bold">Contraseña</span>
                <input
                  v-model="newPassword"
                  type="password"
                  class="form-control"
                />
              </div>
            </div>
            <div class="col">
              <div class="input-group">
                <span class="input-group-text fw-bold">Confirmar</span>
                <input
                  v-model="verifyPassword"
                  type="password"
                  class="form-control"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="text-center mt-4">
          <button
            @click.prevent="createUpdateRequest"
            class="btn btn-primary rounded-pill px-5 fw-bold"
          >
            Actualizar Perfil
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<style scoped>
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}
.title {
  font-family: "LemonMilkBold";
  font-size: 3em;
}
.headline {
  font-family: "LemonMilkBold";
}
form {
  background-color: rgb(86, 82, 63);
}
</style>
