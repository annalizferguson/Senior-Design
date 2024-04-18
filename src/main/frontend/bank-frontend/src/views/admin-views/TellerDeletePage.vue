<template>
  <div
      class="d-flex justify-center align-center"
      style="height: calc(100vh - 70px)"
  >
    <TellerDeleteComponent />
  </div>
</template>

<script>
import TellerDeleteComponent from "@/components/admin-components/TellerDeleteComponent.vue";
export default {
name: "TellerDeletePage.vue",
components: {TellerDeleteComponent}
}
</script>

<style scoped>

</style>