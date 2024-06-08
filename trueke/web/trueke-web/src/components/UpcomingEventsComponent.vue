<script setup>
import useEvent from "@/stores/event";
import { Suspense, computed, onMounted, ref } from "vue";

const eventStore = useEvent();
const {eventList} = eventStore;

const computedEventList = computed(()=>eventStore.event.eventList);
computedEventList && computedEventList >0 ? console.log("ESTAMOS MI GENTE") : console.log("NOSTAMOS")


const uri = import.meta.env.VITE_VUE_APP_API_URL

const threeMostRecentUpcomingEvents = computed(() => {
    const sortedEvents = eventList.sort((a, b) => new Date(b.date) - new Date(a.date))
    const threeMostRecentUpcomingEvents = sortedEvents.slice(0,3);
    return threeMostRecentUpcomingEvents;
})

</script>

<template>
  <div class="container-fluid h-100 rounded-5 p-0">
    <div
      id="upcoming-events"
      class="carousel slide carousel-fade h-100 w-100 bg-secondary p-1 rounded-5"
      data-bs-ride="carousel"
    >
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#upcoming-events" data-bs-slide-to="0" class="active bg-dark" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#upcoming-events" data-bs-slide-to="1" class="bg-dark" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#upcoming-events" data-bs-slide-to="2" class="bg-dark" aria-label="Slide 3"></button>
    </div>
      <div class="carousel-inner h-100 w-100 rounded-5">
        <div 
        v-for="elm,index in threeMostRecentUpcomingEvents" :key="elm.title"
          class="carousel-item container h-100 w-100"
          data-bs-interval="9000"
          :class="{ 'active': index === 0 }"
          :style="`
            background-image: url('${uri}/download/${elm.imgUrl}');
            background-size: cover;
            background-position: center;
            filter: grayscale(0.7);
          `"
        >
          <div class="row h-25 d-flex align-items-center text-center">
            <span
              id="organizer"
              class="font-lemon fs-2 text-light border-top border-bottom border-4"
              >{{ elm.organizer }}</span
            >
          </div>
          <div class="row h-50 d-flex align-items-center text-center">
            <span
              id="event"
              class="font-lemon fs-3 text-light border-top border-bottom border-4"
              >{{elm.title}}</span
            >
          </div>
          <div class="row h-25 d-flex align-items-end text-center p-0">
            <div class="row p-0 mx-0 pb-5">
              <span
                id="date"
                class="fw-bold fs-4 text-light border-top border-4"
                >{{elm.date}}</span
              ><br />
              <span
                id="location"
                class="fw-bold fs-4 text-light border-bottom border-4"
                >{{elm.location}}</span
              >
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- <div v-else class="spinner-border" role="status">
        <span class="visually-hidden">Loading...</span>
    </div> -->
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
#organizer {
  background-color: rgba(0, 0, 0, 0.591);
}
#event {
  background-color: rgba(0, 0, 0, 0.591);
}
#date {
  background-color: rgba(0, 0, 0, 0.591);
}
#location {
  background-color: rgba(0, 0, 0, 0.591);
}
</style>
