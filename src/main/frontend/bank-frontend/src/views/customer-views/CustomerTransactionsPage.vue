<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 100px)"
    >
        <TransactionReportComponent/>
    </div>
</template>

<script>
import TransactionReportComponent from "@/components/customer-components/TransactionReportComponent.vue";

export default {
    name: "ViewCustomerTransactions.vue",
    components: {TransactionReportComponent}

}
</script>

<style scoped>

</style>