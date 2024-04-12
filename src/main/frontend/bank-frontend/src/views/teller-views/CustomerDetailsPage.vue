<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 70px)"
    >
        <CustomerDetailsComponent />
    </div>
</template>

<script>
import CustomerDetailsComponent from "@/components/teller-components/CustomerDetailsComponent.vue";
export default {
    name: "CustomerDetailsPage.vue",
    components: {CustomerDetailsComponent}
}
</script>

<style scoped>

</style>