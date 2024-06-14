<script setup>
const props = defineProps({
  id: String,
  title: String,
  imgUrl: String,
  totalCollaborators: Number,
  date: String,
  location: String
});
const uri = import.meta.env.VITE_VUE_APP_API_URL;

const daysBeetwenNow = (date) =>{
  let today = new Date();
  let targetDate = parseDate(date);
  const timeDiff = today.getTime() - targetDate.getTime();
  const daysDiff = Math.floor(timeDiff / (1000 * 60 * 60 * 24));
  return -daysDiff;
}


const parseDate = (dateString) =>{
  const [day, month, year] = dateString.split("/")
  let dateParsed = new Date(parseInt(year), parseInt(month) - 1, parseInt(day))
  return dateParsed
}


</script>

<template>
  <div
    class="row h-15 mb-3 mx-3 rounded-3 overflow-hidden border-top border-bottom border-end border-4 border-dark p-0 position-relative"
  >

    <div class="col-3 bg-dark d-flex align-items-center p-0">
      <img
        class="border border-dark border-4 rounded-3"
        width="100%"
        :src="`${uri}/download/${props.imgUrl}`"
        alt="event img"
      />
    </div>
    <div class="col container-fluid w-100 h-100 bg-warning-subtle">
      <div class="row text-center">
        <span class="font-lemon text-secondary">{{ props.title }}</span>
      </div>
      <div class="row h-75">
        <div class="col-6 bg-secondary-subtle rounded-end-4 h-100">
          
          <span class="font-lemon align-middle">Total Apuntados: <span class="text-primary">{{props.totalCollaborators}}</span></span
          ><br />
          <span class="fst-italic"
            >Lugar: {{props.location}} || fecha: {{props.date}}</span
          ><br>
          <span class="fst-italic" v-if="daysBeetwenNow(props.date)>=0"
            >faltan: <span class="text-success fw-bold">{{daysBeetwenNow(props.date)}}</span> días</span
          >
          <span class="fst-italic" v-else
            >terminó hace: <span class="text-danger fw-bold">{{-daysBeetwenNow(props.date)}}</span> días</span
          >
        </div>
        <div class="col-2 d-flex align-items-center justify-content-center">
          <!-- <button id="enter-buttom" class="btn btn-success rounded-4 font-lemon">Ver Detalles</button> -->
          <router-link
            class="btn btn-success rounded-4 font-lemon"
            :to="{name: 'association-event-detail', params:{id: props.id}}"
            >Ver</router-link
          >
        </div>
        <div class="col-2 d-flex align-items-center justify-content-center">
          <!-- <button id="enter-buttom" class="btn btn-success rounded-4 font-lemon">Ver Detalles</button> -->
          <router-link
            class="btn btn-warning rounded-4 font-lemon"
            
            >Editar</router-link
          >
        </div>
        <div class="col-2 d-flex align-items-center justify-content-center">
          <!-- <button id="enter-buttom" class="btn btn-success rounded-4 font-lemon">Ver Detalles</button> -->
          <router-link
            class="btn btn-danger rounded-4 font-lemon"
            
            >Borrar</router-link
          >
        </div>
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
#enter-buttom {
  font-size: 0.9em;
}</style>
