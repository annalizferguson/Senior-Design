<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 100px)"
    >
        <CustomerReportComponent />
    </div>
</template>

<script>
import CustomerReportComponent from "@/components/teller-components/CustomerReportComponent.vue";

export default {
    name: "ViewCustomerTransactions.vue",
    components: {CustomerReportComponent}

}
</script>

<style scoped>

</style>