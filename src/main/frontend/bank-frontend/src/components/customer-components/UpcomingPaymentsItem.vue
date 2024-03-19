<template>
    <v-card
            width="100%"
            height="85cd%"
            variant="tonal"
    >
        <v-container
                class="d-flex justify-space-between"
        >
            <b>Name</b>
            <div>{{ billName }}</div>
        </v-container>
        <v-container
                class="d-flex justify-space-between"
        >
            <b>Date Due</b>
            <div>{{ billDate }}</div>
        </v-container>
        <v-container
                class="d-flex justify-space-between"
        >
            <b>Amount</b>
            <div style="font-size: 2em">${{ billAmount }}</div>
        </v-container>
    </v-card>
</template>

<script>
import testPayment from "@/test-files/testPayment.json"

export default {
    name: "UpcomingPaymentsItem.vue",
    props: {
        bill: {
            type: Object
        }
    },
    data: function() {
        return {
            billName: this.bill.name,
            billDate: this.bill["due date"],
            billAmount: this.bill.amount
        }
    }
}
</script>

<style scoped>

</style>