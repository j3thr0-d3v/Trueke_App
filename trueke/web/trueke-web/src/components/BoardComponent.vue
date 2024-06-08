<script setup>
import EventList from '@/components/EventListComponent.vue'
import useEvent from '@/stores/event';
import { onMounted, ref } from 'vue';

const eventStore = useEvent();

const eventList = ref([])

onMounted(async () => {
    await eventStore.getAllEvents();
    eventList.value = eventStore.eventList
})

</script>

<template>
    <div class="container-fluid h-100 bg-white rounded-5 overflow-hidden position-relative">
        <div id="event-navigation" class="row h-10 justify-content-center align-items-center border-bottom ">
            <div class="btn-group align-items-center" role="group" aria-label="Navigation Events">
                <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                <label class="btn btn-outline-dark py-auto rounded-start-pill align-middle" for="btnradio1">Eventos</label>

                <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                <label class="btn btn-outline-dark" for="btnradio2">Mis Colaboraciones</label>

                <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
                <label class="btn btn-outline-dark" for="btnradio3">Por Comenzar</label>

                <input type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off">
                <label class="btn btn-outline-dark rounded-end-pill" for="btnradio4">finalizados</label>
            </div>
        </div>
        <div id="event-list" class="row h-70">
            <EventList :eventList="eventList"></EventList>
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

.font-lemon{
    font-family: 'LemonMilkBold';
}

.selector-tag{
    color: rgb(110, 9, 199);
}

</style>