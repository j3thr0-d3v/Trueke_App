<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import ProfileComponent from "@/components/ProfileComponent.vue";
import BoardComponent from "@/components/BoardComponent.vue";
import UpcomingEventsComponent from '@/components/UpcomingEventsComponent.vue';
import { onMounted, ref } from "vue";
import useEvent from "@/stores/event";

const eventStore = useEvent();
const eventList = ref([])

onMounted(async () => {
    await eventStore.getAllEvents();
    eventList.value = eventStore.eventList
})

</script>

<template>
  <div id="wrapper" class="container-fluid vh-100">
    <div id="header" class="row h-10">
      <HeaderComponent></HeaderComponent>
    </div>
    <div id="middle view" class="row h-90">
      <div id="profile-info" class="col-3 h-100 p-4">
        <ProfileComponent></ProfileComponent>
      </div>
      <div id="main-info" class="col-7 h-100 p-4">
        <BoardComponent :eventList/>
      </div>
      <div id="advertising" class="col-2 h-100 p-4">
        <UpcomingEventsComponent :eventList/>
      </div>
    </div>
  </div>
</template>

<style scoped>
#profile-info, #main-info, #advertising{
  background-color: burlywood;
}
</style>
