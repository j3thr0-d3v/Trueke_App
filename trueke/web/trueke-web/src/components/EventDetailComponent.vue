<script setup>
import router from "@/router";
import useAuth from "@/stores/auth";
import useEvent from "@/stores/event";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

const authStore = useAuth();
const eventStore = useEvent();
const route = useRoute();
const event = ref({});
const uri = import.meta.env.VITE_VUE_APP_API_URL;

const subscribeToEvent = async () => {
  await authStore.singUpEvent(route.params.id);
  router.push("/");
};

const unregisterFromEvent = async () => {
  await authStore.unregisterFromEvent(route.params.id);
  router.push("/")
}
onMounted(async () => {
  await eventStore.getEventDetails(route.params.id);
  event.value = eventStore.event;
});

console.log("parametros de la ruta"+route.path)

const isSubscribed = () => {
  if (
    authStore.user.collaborations &&
    authStore.user.collaborations.length > 0
  ) {
    const foundCollaboration = authStore.user.collaborations.find(
      (obj) => obj.id === event.value.id
    );
    return foundCollaboration;
  } else {
    return false;
  }
};

router.getRoutes().forEach(r => console.log(r))
</script>

<template>
  <div
    id="detail"
    class="container-fluid h-100 overflow-y-scroll position-relative"
  >
    <button
      class="btn btn-dark text-warning border border-2 border-warning fs-5 position-fixed mt-3 rounded-circle"
      @click="$router.back()"

      ><i class="bi bi-arrow-left"></i
    ></button>

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
      <span class="font-lemon fs-4">Descripción:</span><br />
      <span class="font-lemon text-secondary">{{ event.description }}</span>
    </div>
    <div class="row px-5 my-3 text-center">
        <div
        v-if="isSubscribed()"
        @click="unregisterFromEvent"
        class="col-6 d-flex align-items-center justify-content-center"
      >
        <button class="btn btn-danger font-lemon px-5 py-3 rounded-pill">
            <i class="bi bi-x-square me-3"></i> Quitarse
        </button>
      </div>
    <div v-else
      class="col-6 d-flex align-items-center justify-content-center">
        <button
          @click="subscribeToEvent"
          class="btn btn-success font-lemon px-5 py-3 rounded-pill"
        >
        <i class="bi bi-check-square me-3"></i> ¡Apuntarse!
        </button>
      </div>

      <div class="col-6">
        <span class="font-lemon"><i class="bi bi-geo-alt-fill"></i> Lugar:</span
        ><br />
        <span class="fw-bold text-secondary"
          >El evento se celebrará en
          <span class="fst-italic text-dark">"{{ event.location }}"</span></span
        ><br />
        <span class="font-lemon"
          ><i class="bi bi-calendar"></i> Fecha y Hora</span
        ><br />
        <span class="fw-bold text-secondary"
          >El día <span class="fst-italic text-dark">{{ event.date }} </span> de
          <span class="fst-italic text-dark">{{ event.startTime }} </span> a
          <span class="fst-italic text-dark"> {{ event.endTime }} </span></span
        >
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
}
</style>
