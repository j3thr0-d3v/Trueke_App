<script setup>
import useAuth from '@/stores/auth';
import { ref } from 'vue';

const authStore = useAuth();

</script>

<template>
  <nav class="navbar navbar-dark bg-dark navbar-expand-lg container-fluid p-0">
    <div class="row container-fluid">
        <div class="col-lg-6 col-10 p-0">
            <img src="../assets/g4lKbU01.svg" width="10%" alt="Logo">
            <span class="text-white font-lemon fs-1 align-middle">TRUEKE APP </span>
            <span class="text-white font-lemon fs-3 align-middle d-none d-sm-inline">( </span>
            <span class="text-danger font-lemon fs-3 align-middle d-none d-sm-inline">Association </span>
            <span class="text-white font-lemon fs-3 align-middle d-none d-sm-inline">) </span>
        </div>
        <button class="navbar-toggler col-2 my-2" type="button" data-bs-toggle="collapse" data-bs-target="#collapse" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
        <div class="col-lg-6 col-4 p-0 text-end collapse justify-content-end navbar-collapse" id="collapse">
            <i class="bi bi-gear text-warning align-middle fs-2 d-none d-sm-inline mx-3"></i>
            <span class="font-lemon text-danger align-middle d-none d-sm-inline me-2">{{ authStore.user.association }}</span>
            <span class="font-lemon text-white align-middle d-none d-sm-inline">({{authStore.roles.replace(",",", ")}})</span>
            <i class="bi bi-person text-warning align-middle fs-2 d-none d-sm-inline mx-3"></i>
            <div class="btn-group d-none d-sm-inline" role="group">
                <button type="button" class="font-lemon btn btn-outline-warning fs-5">{{authStore.user.username}}</button>
                <button type="button" class="font-lemon btn btn-outline-danger border-start-0 fs-5"><i class="bi bi-box-arrow-right"></i></button>
            </div>
        </div>
    </div>
  </nav>
</template>

<style scoped>
@font-face {
  font-family: "LemonMilkBold";
  src: url("../assets/font/LEMONMILK-Bold.otf");
}
.font-lemon {
  font-family: "LemonMilkBold";
}
</style>
