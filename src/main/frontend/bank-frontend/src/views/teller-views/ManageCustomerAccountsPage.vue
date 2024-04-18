<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 65px)"
    >
        <CustomerAccountsComponent />
    </div>
</template>

<script>
import CustomerAccountsComponent from "@/components/teller-components/CustomerAccountsComponent.vue";
export default {
    name: "ManageCustomerAccountsPage.vue",
    components: {CustomerAccountsComponent}
}
</script>

<style scoped>

</style>