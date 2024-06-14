<script setup>
import router from "@/router";
import useAuth from "@/stores/auth";
import useEvent from "@/stores/event";
import axios from "axios";
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const eventStore = useEvent();
const authStore = useAuth();

const uri = import.meta.env.VITE_VUE_APP_API_URL

const eventImg = ref("");
const eventImgPreview = ref(
  "https://t3.ftcdn.net/jpg/02/48/42/64/360_F_248426448_NVKLywWqArG2ADUxDq6QprtIzsF82dMF.jpg"
);

const title =ref('');
const headline = ref('');
const description = ref('');
const location = ref('');
const date = ref('');
const startTime = ref('');
const endTime = ref('');

const pathCorrection = () => {
  return route.path.split("/").includes("create") ? "crear" : "editar";
};

function loadEventImg(event) {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      eventImgPreview.value = e.target.result;
      eventImg.value = file;
    };
    reader.readAsDataURL(file);
  }
}

function obtainDateTimeFormat(date, time){
    return date.concat("T"+time+":00Z");
}


async function createEventRequest(){
    let eventRequest = {
        title: title.value,
        headline: headline.value,
        description: description.value,
        location: location.value,
        startDate: obtainDateTimeFormat(date.value, startTime.value),
        endDate: obtainDateTimeFormat(date.value, endTime.value)
    };

    console.log(date.value)

    await eventStore.createEvent(authStore.user.association.id, eventRequest, eventImg.value);
    router.push("/association")
}

const loadImg = async() =>{
    const response = await axios.get(`${uri}/download/${eventStore.event.imgUrl}`);
    return response.data;
}

async function loadEvent(){
    if(route.params.id){
        title.value = eventStore.event.title;
        headline.value = eventStore.event.value;
        description.value = eventStore.event.description;
        location.value = eventStore.event.location;
        date.value = eventStore.event.date;
        startTime.value = eventStore.event.startTime;
        endTime.value = eventStore.event.endTime;
        eventImgPreview.value = `${uri}/download/${eventStore.event.imgUrl}`;
        eventImg.value = await loadImg()
        
    }else{
        console.log("Not editing");
    }
}


onMounted(async () => {
  pathCorrection();
  await loadEvent();
});
</script>

<template>
  <div class="container-fluid bg-dark vh-100">
    <div class="row h-10 align-items-center justify-content-center">
      <div
        class="col-3 text-center border border-warning border-2 rounded-pill"
      >
        <span class="text-warning font-lemon fs-3"
          >{{ pathCorrection() }} Evento</span
        >
      </div>
    </div>
    <div id="form" class="row h-75 align-items-center justify-content-center">
      <div
        class="col-7 h-100 rounded-5 border border-3 bg-yellow-ligth border-warning overflow-hidden py-3"
      >
        <div class="col-12 text-center">
          <div
            class="event-img mx-auto border border-3 border-warning rounded-4 overflow-hidden"
            :style="`background-image: url('${eventImgPreview}'); background-size:cover; background-position:center;`"
          ></div>
          <label for="event" class="form-label text-white fw-bold"
            >Imagen del Evento</label
          >
          <input
            @change="loadEventImg"
            class="form-control form-control-sm w-50 mx-auto"
            id="event"
            type="file"
          />
        </div>
        <div class="row mt-4 ">
          <div class="col">
            <div class="input-group">
              <span class="input-group-text fw-bold">Título del Evento</span>
              <input
                v-model="title"
                type="text"
                class="form-control"
                placeholder="ej... Recogida de Basura en el Parque"
                aria-label="Título"
              />
            </div>
          </div>
        </div>
        <div class="row mt-4 ">
          <div class="col">
            <div class="input-group">
              <span class="input-group-text fw-bold">Cabecera/Reclamo</span>
              <input
                v-model="headline"
                type="text"
                class="form-control"
                placeholder="ej... !Únete junto a la asociación ¨X¨ a la limpieza del barrio!"
                aria-label="reclamo"
              />
            </div>
          </div>
        </div>
        <div class="row ">
            <div class="form-floating mt-4">
                <textarea class="form-control" placeholder="Leave a comment here" id="career" style="height: 145px" v-model="description"></textarea>
                <label for="career" class="fw-bold text-secondary ms-2">Descripción detallada del evento</label>
            </div>
        </div>
        <div class="row mt-4 ">
          <div class="col">
            <div class="input-group">
              <span class="input-group-text fw-bold">Dirección del evento</span>
              <input
                v-model="location"
                type="text"
                class="form-control"
                placeholder="ej... P. Sherman Calle Wallaby 42, Sidney"
                aria-label="direccion"
              />
            </div>
          </div>
        </div>
        <div class="row mt-4 justify-content-beetween">
            <div class="col-4 text-center">
                <label for="date" class="text-white fw-bold">Fecha</label>
                <input id="date" type="date" class="form-control" v-model="date">
            </div>
            <div class="col-4 text-center">
                <label for="timeStart" class="text-white fw-bold">Hora de inicio</label>
                <input id="timeStart" type="time" class="form-control" v-model="startTime">
            </div>
            <div class="col-4 text-center">
                <label for="timeEnd" class="text-white fw-bold">Hora de finalización</label>
                <input id="timeEnd" type="time" class="form-control" v-model="endTime">
            </div>
        </div>
      </div>
    </div>
    <div class="row h-15 align-items-center">
      <div class="text-center">
        <button type="button" class="btn btn-primary rounded-pill font-lemon fs-3 w-25" @click.prevent="createEventRequest">
          {{ pathCorrection() }} Evento
        </button>
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
.bg-yellow-ligth {
  background-color: rgb(86, 82, 63);
}
.event-img {
  width: 110px;
  height: 80px;
  background-color: aliceblue;
}
</style>
