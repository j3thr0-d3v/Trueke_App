<script setup>
import { ref } from 'vue';
import useAuth from '@/stores/auth';
import router from '@/router';

const store = useAuth()

const name = ref('')
const surname = ref('')
const email = ref('')
const username = ref('')
const dateOfBirth = ref(null)
const skills = ref('')
const motivation = ref('')
const password = ref('')
const verifyPassword=ref('')
const imgProfilePreview = ref('https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1665px-No-Image-Placeholder.svg.png')
const imgProfile = ref('')

function obtainAge(){
    const today = new Date()
    const birthDate = new Date(dateOfBirth.value);
    const diffInMilliseconds = today.getTime() - birthDate.getTime();
    const years = Math.floor(diffInMilliseconds / (1000 * 60 * 60 * 24 * 365));
    return years
}

const handleFileChange = (event)=>{
    const file = event.target.files[0];
    if(file){
        const reader = new FileReader();
        reader.onload = (e) =>{
            imgProfilePreview.value = e.target.result;
            imgProfile.value = file
        };
        reader.readAsDataURL(file)
    }
}



const createUserCollaborator = () => {
    let singUpCollaboratorRequest = {
        name: name.value,
        surname: surname.value,
        email: email.value,
        username: username.value,
        age: obtainAge(),
        skills: skills.value,
        motivation: motivation.value,
        password: password.value,
        verifyPassword: verifyPassword.value
    }

    store.registerCollaborator(singUpCollaboratorRequest, imgProfile.value)
    router.push({name:'login'})
}
</script>

<template>
    <div class="container-fluid bg-dark h-100 vh-100 p-4">
        <div class="vh-5  mb-4 d-flex justify-content-center align-items-center">
            <span class="headline text-warning border border-3 border-warning rounded-pill px-5">Formulario de registro</span>
        </div>
        <div class="container w-50">
            <form class="needs-validation p-4 border-warning border-3 border rounded-5">
                <div class="row mb-3">
                    <div class="col">
                        <div class="h-10 text-center">
                            <img class="rounded-4 h-100 border border-3 border-warning" :src="imgProfilePreview"  alt="uploaded photo">
                        </div>
                        <div class="text-center mx-auto mt-3 w-50">
                            <label for="photo" class="fw-bold text-white">Seleciona foto de perfil</label>
                            <input id="photo" class="form-control" @change="handleFileChange" type="file" accept="image/*" >
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
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
                <div class="row mt-3">
                    <div class="col">
                        <div class="input-group">
                            <span class="input-group-text fw-bold">Email</span>
                            <input v-model="email" type="email" class="form-control" placeholder="ej... correo@correo.mail" aria-label="mail">
                        </div>
                    </div>
                    <div class="col">
                        <div class="input-group">
                            <span class="input-group-text fw-bold">Nick</span>
                            <input v-model="username" type="text" class="form-control" placeholder="ej... migue93" aria-label="nombre de usuario">
                        </div>
                    </div>
                </div>
                <div class="row mx-auto w-50">
                    <div class=" mt-3 ">
                        <div class="input-group">
                            <span class="input-group-text fw-bold">Fecha de Nacimiento</span>
                            <input v-model="dateOfBirth" type="date" class="form-control" placeholder="ej... migue93" aria-label="nombre de usuario">
                        </div>
                    </div>
                </div>
                <div class="row mt-3 w-100 mx-auto">
                    <h4 class="text-white text-center mt-3">Motivaciones</h4>
                    <textarea v-model="motivation" class="form-control w-100" placeholder="¿Por qué deseas unirte?"></textarea>
                </div>
                <div class="row mt-3 w-100 mx-auto">
                    <h4 class="text-white text-center mt-3">Habilidades</h4>
                    <textarea v-model="skills" class="form-control w-100" placeholder="¿Qué habilidades o conocimientos posees?"></textarea>
                </div>
                <h4 class="text-white text-center mt-3">Contraseña</h4>
                <div class="row mt-3">
                    <div class="col">
                        <div class="input-group">
                            <span class="input-group-text fw-bold">Contraseña</span>
                            <input v-model="password" type="password" class="form-control">
                        </div>
                    </div>
                    <div class="col">
                        <div class="input-group">
                            <span class="input-group-text fw-bold">Confirmar</span>
                            <input v-model="verifyPassword" type="password" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="text-center mt-4">
                    <button @click.prevent="createUserCollaborator" class="btn btn-primary rounded-pill px-5 fw-bold">Registrarse</button>
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
.title{
  font-family: 'LemonMilkBold';
  font-size: 3em
}
.headline{
    font-family: 'LemonMilkBold';
}
form{
    background-color: rgb(86, 82, 63);
}
</style>