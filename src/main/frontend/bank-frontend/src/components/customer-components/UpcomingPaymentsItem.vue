<template>
    <v-card
            width="100%"
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
        <v-card-actions class="d-flex justify-end">
            <v-btn
                @click="dialogActive = true"
            >
                Pay Now
            </v-btn>
            <v-dialog
                v-model="dialogActive"
                width="50%"
            >
                <MakeAPaymentComponent :bill="bill"/>
                <v-btn
                    color="#4097f5"
                    @click="dialogActive = false"
                >
                    Cancel
                </v-btn>
            </v-dialog>
        </v-card-actions>
    </v-card>
</template>

<script>
import MakeAPaymentComponent from "@/components/customer-components/MakeAPaymentComponent.vue";

export default {
    name: "UpcomingPaymentsItem.vue",
    components: {MakeAPaymentComponent},
    props: {
        bill: {
            type: Object
        }
    },
    data: function() {
        return {
            dialogActive: false,
            billName: this.bill.name,
            billDate: this.bill["due date"],
            billAmount: this.bill.amount
        }
    }
}
</script>

<style scoped>

</style>