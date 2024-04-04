<template>
    <v-card>
        <v-card-title style="background-color: #4097f5; color: #ffffff">Pay Bill Now</v-card-title>
        <v-card-text>
            <v-form class="mt-5">
                <v-select
                        v-model="selectedBill"
                        label="Select Bill"
                        :items="bills"
                        item-title="title"
                        return-object
                        variant-outlined
                        @update="amountToPay = 0"
                />
                <v-container v-if="selectedBill">
                    <b>Amount Due:</b> ${{ selectedBillAmount }}
                    <v-text-field
                            v-model="amountToPay"
                            name="amountToPay"
                            label="Amount to Pay"
                            type="number"
                            placeholder="Enter amount of money to pay off the bill"
                            variant="outlined"
                            prepend-icon="mdi-currency-usd"
                            class="mt-4"
                            clearable
                            hide-spin-buttons
                            required
                    />
                    <div class="d-flex justify-center">
                        <v-btn
                                v-model="payInFull"
                                @click="amountToPay = selectedBill.amountDue"
                                color="#4097f5"
                                variant="tonal"
                        >
                            Pay Bill In Full
                        </v-btn>
                    </div>
                </v-container>
                <v-container class="d-flex justify-end">
                    <v-btn color="#4097f5">Pay Now</v-btn>
                </v-container>
            </v-form>
        </v-card-text>
    </v-card>
</template>

<script>
import axios from 'axios';
import {useCustomerStore} from "@/states/UserStore.js";

export default {
    name: "MakeAPaymentComponent.vue",
    props: {
        bill: {
            type: Object
        },
    },
    data: function () {
        const store = useCustomerStore()
        return {
            store: store,
            billsLoaded: false,
            bills: [],
            selectedBill: this.bill,
            amountToPay: 0,
            payInFull: false,
        }
    },
    computed: {
        selectedBillAmount() {
            return this.selectedBill.amountDue
        },
    },
    methods: {
        async loadUnpaidBills() {
            const id = this.store.getID
            const {data} = await axios.get(`/api/customers/${id}/unpaidbill`)
            console.log(data)
            this.bills = data
            this.accountsLoaded = true

            this.bills.forEach((bill) => {
                bill.title = bill.payeeName + ": Due " + bill.dueDate
            })
            if (this.bill) {
                this.bill.title = this.bill.payeeName + ": Due " + this.bill.dueDate
            }
        }
    },
    beforeMount() {
        this.loadUnpaidBills()
    }
}
</script>

<style scoped>

</style>