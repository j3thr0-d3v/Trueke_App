<script setup>
import { ref } from 'vue';

//IMG VALUES
const banner = ref('');
const bannerPreview = ref('https://t3.ftcdn.net/jpg/02/48/42/64/360_F_248426448_NVKLywWqArG2ADUxDq6QprtIzsF82dMF.jpg');
const logo = ref('');
const logoPreview = ref('https://t3.ftcdn.net/jpg/02/48/42/64/360_F_248426448_NVKLywWqArG2ADUxDq6QprtIzsF82dMF.jpg');
const profile = ref('');
const profilePreview = ref('https://t3.ftcdn.net/jpg/02/48/42/64/360_F_248426448_NVKLywWqArG2ADUxDq6QprtIzsF82dMF.jpg');

//ASOCIATION VALUES
const associationName = ref('');
const address = ref('');
const mission = ref('');
const cif = ref('');

//FOUNDER VALUES
const name = ref('');
const surname = ref('');
const email = ref('');
const username = ref('');


//METHODS
function loadLogo(event){
  const file = event.target.files[0];
  if(file){
        const reader = new FileReader();
        reader.onload = (e) =>{
            logoPreview.value = e.target.result;
            logo.value = file
        };
        reader.readAsDataURL(file)
    }
}

function loadBanner(event){
  const file = event.target.files[0];
  if(file){
        const reader = new FileReader();
        reader.onload = (e) =>{
            bannerPreview.value = e.target.result;
            banner.value = file
        };
        reader.readAsDataURL(file)
    }
}

function loadProfile(event){
  const file = event.target.files[0];
  if(file){
        const reader = new FileReader();
        reader.onload = (e) =>{
            profilePreview.value = e.target.result;
            profile.value = file
        };
        reader.readAsDataURL(file)
    }
}

//SUBMIT
function createAssociationRequest(){

  let request = {
    name: associationName.value,
    addres: address.value,
    mission: mission.value,
    cif: cif.value,
    founder: {
      name: name.value,
      surname: surname.value,
      username: username.value,
      email: email.value,
    }
  }

}



</script>

<template>
  <div class="container-fluid bg-dark vh-100">
    <div class="row h-10 align-items-center justify-content-center">
      <div class="col-3 text-center border border-warning border-2 rounded-pill">
        <span class="text-warning font-lemon fs-3">Registro Asociación</span>
      </div>
    </div>
    <div id="form" class="row h-75 align-items-center justify-content-center">
      <div class="col-5 h-100 rounded-start-5 border border-3 bg-yellow-ligth border-warning border-end-0 overflow-hidden py-3 ps-3 pe-0">
        <div class="w-100 h-100 border-end border-1 border-warning">

          <p class="text-warning font-lemon text-center me-3">Datos de la Asociación</p>
          <div class="container-fluid p-0">
            <div class="row me-1">
              <div class="col-6 text-center">
                <div class="logo mx-auto border border-3 border-warning rounded-4 overflow-hidden" :style="`background-image: url('${logoPreview}'); background-size:cover; background-position:center;`"></div>
                <label for="logo" class="form-label text-white fw-bold">Logo de la Asociación</label>
                <input @change="loadLogo" class="form-control form-control-sm" id="logo" type="file">
              </div>
              <div class="col-6 text-center">
                <div class=" banner mx-auto border border-3 border-warning rounded-4 overflow-hidden" :style="`background-image: url('${bannerPreview}'); background-size:contain;background-repeat:no-repeat; background-position:center;`"></div>
                <label for="banner" class="form-label text-white fw-bold">Banner de la Asociación</label>
                <input @change="loadBanner" class="form-control form-control-sm" id="banner" type="file">
              </div>
            </div>
            <div class="row me-1">
              <div class="form-floating mt-3">
                <input type="text" class="form-control" id="associationName" placeholder="Nombre" v-model="associationName">
                <label for="associationName" class="fw-bold text-secondary ms-2">Nombre de la asociación</label>
              </div>
              <div class="form-floating mt-3">
                <input type="text" class="form-control" id="associationAddress" placeholder="Nombre" v-model="address">
                <label for="associationAddress" class="fw-bold text-secondary ms-2">Dirección</label>
              </div>
              <div class="form-floating mt-3">
                <textarea class="form-control" placeholder="Leave a comment here" id="mission" style="height: 200px" v-model="mission"></textarea>
                <label for="mission" class="fw-bold text-secondary ms-2">Describe la misión de la asociación</label>
              </div>
              <div class="input-group mt-3">
                <span class="input-group-text text-secondary fw-bold">CIF</span>
                <input type="text" class="form-control" v-model="cif">
              </div>
            </div>
          </div>
        
        </div>
      </div>

      <div class="col-5 h-100 rounded-end-5 border-top border border-3  bg-yellow-ligth border-warning border-start-0 overflow-hidden py-3 pe-3 ps-0">
        <div class="w-100 h-100 border-start border-1 border-warning">

          <p class="text-warning font-lemon text-center ms-3">Datos del fundador</p>
          <div class="container-fluid p-0">
            <div class="row ms-1">
              <div class="col-12 text-center">
                <div class="profile mx-auto border border-3 border-warning rounded-4 overflow-hidden" :style="`background-image: url('${profilePreview}'); background-size:cover; background-position:center;`"></div>
                <label for="logo" class="form-label text-white fw-bold">Logo de la Asociación</label>
                <input @change="loadProfile" class="form-control form-control-sm w-50 mx-auto" id="logo" type="file">
              </div>
            </div>
            <div class="row mt-3 ms-1">
              <div class="col">
                <div class="input-group">
                  <span class="input-group-text fw-bold">Nombre</span>
                  <input v-model="name" type="text" class="form-control" placeholder="ej... Miguel Angel" aria-label="Nombre">
                </div>
              </div>
              <div class="col">
                <div class="input-group">
                  <span class="input-group-text fw-bold">Apellidos</span>
                  <input v-model="surname" type="text" class="form-control" placeholder="ej... Pérez Rodríguez" aria-label="Apellidos">
                </div>
              </div>
            </div>
            <div class="row mt-3 ms-1">
              <div class="col">
                <div class="input-group">
                  <span class="input-group-text fw-bold">@ Correo</span>
                  <input v-model="email" type="text" class="form-control" placeholder="ej... Miguel Angel" aria-label="Nombre">
                </div>
              </div>
              <div class="col">
                <div class="input-group">
                  <span class="input-group-text fw-bold">Nick</span>
                  <input v-model="username" type="text" class="form-control" placeholder="ej... Pérez Rodríguez" aria-label="Apellidos">
                </div>
              </div>
            </div>
            <div class="row mt-3 ms-1">
              <div class="col">
                <div class="input-group">
                  <span class="input-group-text fw-bold">DNI</span>
                  <input v-model="email" type="text" class="form-control" placeholder="ej... Miguel Angel" aria-label="Nombre">
                </div>
              </div>
              <div class="col">
                <div class="input-group">
                  <span class="input-group-text fw-bold">Teléfono</span>
                  <input v-model="username" type="text" class="form-control" placeholder="ej... Pérez Rodríguez" aria-label="Apellidos">
                </div>
              </div>
            </div>
          </div>
          

        </div>
      </div>

    </div>
    <div class="row h-15 align-items-center">
      <div class="text-center">
        <button type="button" class="btn btn-primary rounded-pill font-lemon fs-3 w-25">Registrar</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}
.font-lemon{
    font-family: 'LemonMilkBold';
}
.bg-yellow-ligth{
  background-color: rgb(86, 82, 63);
}
.logo{
  width: 110px;
  height: 80px;
  background-color: aliceblue;
}
.profile{
  width: 110px;
  height: 80px;
  background-color: aliceblue;
}
.banner{
  width: 200px;
  height: 80px;
  background-color: aliceblue;
}
</style>
