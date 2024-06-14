<script setup>
import useAuth from '@/stores/auth';
import useEvent from '@/stores/event';
import { onMounted, ref } from 'vue';

const eventStore = useEvent();
const authStore = useAuth();

const events = ref([])

onMounted(async ()=>{
  await authStore.getUser()
  await eventStore.getAssociationEvents(authStore.user.association.id)
  events.value = eventStore.eventList
})
</script>

<template>
  <div class="container-fluid bg-white h-100 rounded-5 overflow-hidden">
    <div class="row h-10 justify-content-center align-items-center border-bottom ">
        <div class="btn-group align-items-center" role="group">
            <router-link class="font-lemon btn btn-outline-dark py-auto rounded-start-pill align-middle" to="/association">Eventos</router-link>
            <router-link class="font-lemon btn btn-outline-dark py-auto  align-middle" to="/info">Asociación</router-link>
            <router-link class="font-lemon btn btn-outline-dark py-auto rounded-end-pill align-middle" to="/crew">Personal</router-link>
        </div>
    </div>
    <div class="row h-70">
      <router-view v-if="$route.path === '/association'" :events="events"></router-view>
      <router-view v-else></router-view>
    </div>
    <div class="row h-5 border-top">
      <div class="btn-group p-0">
        <router-link to="/association/event/create" class="btn btn-success rounded-0 font-lemon"><i class="bi bi-plus-circle"></i> Crear Evento</router-link>
        <button class="btn btn-warning rounded-0 font-lemon"><i class="bi bi-person-plus-fill"></i> Añadir Trabajador</button>
      </div>
    </div>
    
  </div>
</template>

<style scoped>
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}
.router-link-exact-active{
    background-color: black;
    color: white
}
.font-lemon{
    font-family: 'LemonMilkBold';
}
</style>