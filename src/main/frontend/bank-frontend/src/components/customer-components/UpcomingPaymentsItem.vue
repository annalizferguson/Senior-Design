<template>
    <v-card
            width="100%"
            variant="tonal"
    >
        <v-card-title style="background-color: #4097f5"/>
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
                    color="#4097f5"
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
    data: function () {
        console.log(this.bill)
        return {
            dialogActive: false,
            billName: this.bill.payeeName,
            billDate: this.bill.dueDate,
            billAmount: this.bill.amountDue,
        }
    }
}
</script>

<style scoped>

</style>