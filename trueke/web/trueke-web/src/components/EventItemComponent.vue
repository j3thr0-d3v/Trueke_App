<script setup>

const uri = import.meta.env.VITE_VUE_APP_API_URL

const props = defineProps({
    organizer : String,
    title : String,
    headline: String,
    location: String,
    date: String,
    imgUrl: String
})
</script>

<template>
    <div class="row h-15 mb-3 mx-3 rounded-3 overflow-hidden border-top border-bottom border-end border-4 border-dark p-0">
        <div class="col-3 bg-dark d-flex align-items-center p-0">
                <img class="border border-dark border-4 rounded-3" width="100%" :src="`${uri}/download/${props.imgUrl}`" alt="event img">
        </div>
        <div class="col container-fluid w-100 h-100 bg-warning-subtle">
            <div class="row text-center">
                <span  class="font-lemon text-secondary">{{props.organizer}}</span>
            </div>
            <div class="row">
                <div class="col-10 bg-secondary-subtle rounded-end-4 h-100 ">
                    <span class="font-lemon fs-3 text-center ">{{props.title}}</span><br>
                    <span class="font-lemon align-middle">{{props.headline}}</span><br>
                    <span class="fst-italic">Lugar: {{props.location}} || fecha: {{ props.date }}</span>
                </div>
                <div class="col-2 d-flex align-items-center">
                    <button id="enter-buttom" class="btn btn-success rounded-4 font-lemon">Ver Detalles</button>
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
.font-lemon{
    font-family: 'LemonMilkBold';
}
#enter-buttom{
    font-size: 0.9em;
}
</style>