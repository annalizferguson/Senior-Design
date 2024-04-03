<template>
    <v-container
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 250px)"
    >
        <TransfersComponent width="80%"/>
    </v-container>
</template>

<script>
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";

export default {
    name: "TransfersPage.vue",
    components: {TransfersComponent}
}
</script>

<style scoped>

</style>