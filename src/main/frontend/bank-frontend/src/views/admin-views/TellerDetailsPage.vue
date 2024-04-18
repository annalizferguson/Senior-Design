<template>
  <div
      class="d-flex justify-center align-center"
      style="height: calc(100vh - 70px)"
  >
    <TellerDetailsComponent />
  </div>
</template>

<script>
import TellerDetailsComponent from "@/components/admin-components/TellerDetailsComponent.vue";
export default {
  name: "TellerDetailsPage.vue",
  components: {TellerDetailsComponent}
}
</script>

<style scoped>

</style>