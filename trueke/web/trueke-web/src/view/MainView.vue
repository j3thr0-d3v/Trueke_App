<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import ProfileComponent from "@/components/ProfileComponent.vue";
import BoardComponent from "@/components/BoardComponent.vue";
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
      <div id="main info" class="col-7 bg-danger h-100 p-4">
        <BoardComponent></BoardComponent>
      </div>
      <div id="advertising aside" class="col-2 bg-success"></div>
    </div>
  </div>
</template>

<style scoped>
#profile-info{
  background-color: burlywood;
}
</style>
