<script setup>
import HeaderAssociationComponent from '@/components/HeaderAssociationComponent.vue';
import WorkerProfileComponent from '@/components/WorkerProfileComponent.vue';
import AssociationBoard from '@/components/AssociationBoard.vue';
</script>

<template>
  <div id="wrapper" class="container-fluid vh-100">
    <div id="header" class="row h-10">
      <HeaderAssociationComponent/>
    </div>
    <div id="middle view" class="row h-90">
      <div id="profile-info" class="col-3 h-100 p-4">
        <WorkerProfileComponent/>
      </div>
      <div id="main-info" class="col-9 h-100 p-4">
        <AssociationBoard/>
      </div>
    </div>
  </div>
</template>

<style scoped>
#profile-info, #main-info, #advertising{
  background-color: burlywood;
}

</style>