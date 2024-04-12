<template>

</template>

<script>
export default {
    name: "ManageCustomerAccountsPage.vue",
    props: {
        customerID: {
            type: String
        }
    },
    data: function() {
        return {
            id: this.customerID
        }
    }
}
</script>

<style scoped>

</style>