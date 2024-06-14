<script setup>
import useEvent from '@/stores/event';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

const eventStore = useEvent();
const route = useRoute();

const event = ref({});
const uri = import.meta.env.VITE_VUE_APP_API_URL;

onMounted(async () => {
    await eventStore.getEventDetails(route.params.id);
    event.value = eventStore.event;
})
</script>

<template>
  <div
    id="detail"
    class="container-fluid h-100 overflow-y-scroll position-relative"
  >
    <button
      class="btn btn-dark text-warning border border-2 border-warning fs-5 position-fixed mt-3 rounded-circle"
      @click="$router.back()"
    >
      <i class="bi bi-arrow-left"></i>
    </button>

    <div class="row px-5 text-center">
      <h1 class="font-lemon bg-success text-white py-1 rounded-pill my-3">
        {{ event.title }}
      </h1>
    </div>
    <div class="row px-5">
      <div class="col-5">
        <img
          class="img-fluid rounded border border-3 border-dark"
          :src="`${uri}/download/${event.imgUrl}`"
          alt=""
        />
      </div>
      <div
        class="col-7 align-items-center d-flex flex-column justify-content-center"
      >
        <span class="font-lemon"
          >Organizador:
          <span class="text-danger">{{ event.organizer }}</span></span
        ><br />
        <span class="font-lemon text-dark fs-3">{{ event.headline }}</span
        ><br />
      </div>
    </div>
    <div class="row px-5 my-3">
      <span class="font-lemon fs-4"><i class="bi bi-card-text"></i> Descripción:</span><br />
      <span class="font-lemon text-secondary">{{ event.description }}</span>
      <span class="font-lemon fs-4 mt-3"><i class="bi bi-calendar"></i> Fecha y Hora</span><br />
        <span class="fw-bold text-secondary">El día <span class="fst-italic text-dark">{{ event.date }} </span> de
          <span class="fst-italic text-dark">{{ event.startTime }} </span> a
          <span class="fst-italic text-dark"> {{ event.endTime }} </span></span>
          <span class="font-lemon text-secondary mt-3"><i class="bi bi-person-plus-fill"></i> Total colaboradores: <span class="text-primary">{{ event.totalCollaborators }}</span></span><br />
    </div>
    <div class="row">
        <div class="col-6 text-end">
            <router-link :to="{name:'edit-event', params:{id: event.id}}" class="btn btn-warning font-lemon"><i class="bi bi-pencil-square"></i> Editar</router-link>
        </div>
        <div class="col-6 tex-start">
            <button class="btn btn-danger font-lemon"><i class="bi bi-trash"></i> Borrar</button>
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

#detail {
  scrollbar-color: blue;
}
#detail::-webkit-scrollbar {
  width: 15px;
  background: lightgrey;
}
#detail::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background: darkgrey;
}</style>
