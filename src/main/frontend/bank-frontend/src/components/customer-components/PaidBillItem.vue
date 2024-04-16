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
    </v-card>
</template>

<script>
import MakeAPaymentComponent from "@/components/customer-components/MakeAPaymentComponent.vue";
import {useCustomerStore} from "@/states/UserStore.js";

export default {
    name: "PaidBillItem.vue",
    components: {MakeAPaymentComponent},
    props: {
        bill: {
            type: Object
        },
        customerID: {
            type: Number
        }
    },
    data: function () {
        const store = useCustomerStore()
        return {
            store: store,
            dialogActive: false,
            billName: this.bill.payeeName,
            billDate: this.bill.dateDue,
            billAmount: this.bill.amount,
        }
    }
}
</script>

<style scoped>

</style>