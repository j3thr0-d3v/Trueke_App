<script setup>
import EventItem from '@/components/EventItemComponent.vue'
import { ref } from 'vue';

const props = defineProps({
    eventList: Array
})
</script>

<template>
    <div id="list" class="container-fluid h-100 bg-white overflow-y-scroll">
        <EventItem v-for="elm in props.eventList" :key="elm.title" :title="elm.title" :organizer="elm.organizer" :headline="elm.headline" :location="elm.location" :date="elm.date" :imgUrl="elm.imgUrl"/>
    </div>
</template>

<style scoped>
#list{
    scrollbar-color: blue;
}
#list::-webkit-scrollbar{
    width: 15px;
    background: lightgrey;
}
#list::-webkit-scrollbar-thumb{
    border-radius: 5px;
    background: darkgrey;
}


</style>