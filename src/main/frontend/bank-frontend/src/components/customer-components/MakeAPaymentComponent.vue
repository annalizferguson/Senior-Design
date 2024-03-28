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
                        @click="amountToPay = selectedBill.amount"
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
import testPayments from "@/test-files/testPayments.json"

export default {
    name: "MakeAPaymentComponent.vue",
    props: {
        bill: {
            type: Object
        },
    },
    data: function () {
        let currentBills = testPayments
        currentBills.forEach((bill) => {
            bill.title = bill.name + ": Due " + bill["due date"]
        })
        if (this.bill) {
            this.bill.title = this.bill.name + ": Due " + this.bill["due date"]
        }
        return {
            bills: currentBills,
            selectedBill: this.bill,
            amountToPay: 0,
            payInFull: false,
        }
    },
    computed: {
        selectedBillAmount() {
            return this.selectedBill.amount
        },
    },
}
</script>

<style scoped>

</style>