<script setup>
import AssociationEventListItem from '@/components/AssociationEventListItem.vue'

const props = defineProps({
  events: Array
})
console.log(props.events)
</script>

<template>
  <div id="list" class="container-fluid h-100 bg-white overflow-y-scroll">
    <AssociationEventListItem v-for="elm in props.events" :key="elm.id" :title="elm.title" :imgUrl="elm.imgUrl" :totalCollaborators="elm.totalCollaborators" :date="elm.date" :location="elm.location"/>
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