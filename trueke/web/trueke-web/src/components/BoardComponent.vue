<script setup>
import useAuth from '@/stores/auth';
import { computed } from 'vue';


const authStore = useAuth()

const collaboratorEventList = () => {
    return authStore.user.collaborations
}

const upcomingEvents = () => {
    return collaboratorEventList().filter(
        e => parseDate(e.date) >= new Date()
    )
}

function parseDate(dateString){
  const [day, month, year] = dateString.split("/")
  
  let dateParsed = new Date(parseInt(year), parseInt(month) - 1, parseInt(day))
  
  return dateParsed
}

const totalEventsFinished = () => {
    return collaboratorEventList().filter(e => parseDate(e.date) <= new Date())
}


const props = defineProps({
    eventList: Array
})

</script>

<template>
    <div class="container-fluid h-100 bg-white rounded-5 overflow-hidden position-relative">
        <div id="event-navigation" class="row h-10 justify-content-center align-items-center border-bottom ">
            <div class="btn-group align-items-center" role="group" aria-label="Navigation Events">
                <router-link class="font-lemon btn btn-outline-dark py-auto rounded-start-pill align-middle" to="/">eventos</router-link>

                <router-link class="font-lemon btn btn-outline-dark py-auto align-middle" to="/collaborations">Mis Colaboraciones</router-link>

                <router-link class="font-lemon btn btn-outline-dark py-auto align-middle" to="/upcoming">Por comenzar</router-link>

                <router-link class="font-lemon btn btn-outline-dark py-auto align-middle rounded-end-pill" to="/finished">Finalizados</router-link>
            </div>
        </div>
        <div id="event-list" class="row h-70">
            <router-view v-if="$route.path ==='/'" :eventList="props.eventList"></router-view>
            <router-view v-if="$route.path === '/collaborations'" :eventList="collaboratorEventList()"></router-view>
            <router-view v-if="$route.path === '/upcoming'" :eventList="upcomingEvents()"></router-view>
            <router-view v-if="$route.path === '/finished'" :eventList="totalEventsFinished()"> </router-view>
            <router-view v-else/>
            
        </div>
        <div id="board-footer" class="row h-5 bg-secondary-subtle position-absolute bottom-0 w-100 d-flex align-items-center">
            <span class="text-center font-lemon"><span class="selector-tag">EVENTOS: </span>En total hay disponibles <span class="text-success">{{eventList.length}}</span> eventos</span>
        </div>
    </div>
</template>

<style scoped>
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}

label{
    font-family: 'LemonMilkBold';
}

.router-link-exact-active{
    background-color: black;
    color: white
}

.font-lemon{
    font-family: 'LemonMilkBold';
}

.selector-tag{
    color: rgb(110, 9, 199);
}

</style>