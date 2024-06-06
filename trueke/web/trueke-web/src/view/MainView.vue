<script setup>
import HeaderComponent from "../components/HeaderComponent.vue";
import ProfileComponent from "../components/ProfileComponent.vue";
</script>

<template>
  <div id="wrapper" class="container-fluid vh-100">
    <div id="header" class="row h-10 bg-info">
      <HeaderComponent></HeaderComponent>
    </div>
    <div id="middle view" class="row h-90">
      <div id="profile info" class="col-3 h-100 bg-primary p-4">
        <ProfileComponent></ProfileComponent>
      </div>
      <div id="main info" class="col-7 bg-danger"></div>
      <div id="advertising aside" class="col-2 bg-success"></div>
    </div>
  </div>
</template>

<style scoped></style>
