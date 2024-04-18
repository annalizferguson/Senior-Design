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
                    <v-select
                            v-model="selectedAccount"
                            label="Pay Bill From"
                            :items="accounts"
                            item-title="label"
                            return-object
                    />
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
                <v-alert
                    v-model="paymentSuccess"
                    type="success"
                    closable
                    @close="paymentSuccess = false"
                >
                    Bill paid successfully. You may close this dialog or make another payment.
                </v-alert>
                <v-alert
                    v-model="paymentFail"
                    type="error"
                    closable
                    @close="paymentFail = false"
                >
                    Bill payment failed.
                </v-alert>
                <v-container class="d-flex justify-end">
                    <v-btn color="#4097f5" @click="payBill">Pay Now</v-btn>
                </v-container>
            </v-form>
        </v-card-text>
    </v-card>
</template>

<script>
import axios from 'axios';
import {useCustomerStore} from "@/states/UserStore.js";
import {useTellerStore} from "@/states/TellerStore.js";

export default {
    name: "MakeAPaymentComponent.vue",
    props: {
        bill: {
            type: Object
        },
        customerID: {
            type: Number
        }
    },
    data: function () {
        const customerStore = useCustomerStore()
        const tellerStore = useTellerStore()
        return {
            customerStore: customerStore,
            tellerStore: tellerStore,
            billsLoaded: false,
            bills: [],
            selectedBill: this.bill,
            amountToPay: 0,
            payInFull: false,
            accounts: [],
            selectedAccount: "",
            paymentSuccess: false,
            paymentFail: false,
        }
    },
    computed: {
        selectedBillAmount() {
            return this.selectedBill.amountDue
        },
    },
    methods: {
        async loadAccounts() {
            let id = this.customerStore.getID
            if (!id) {
                id = this.tellerStore.getCustomerID
            }
            const {data} = await axios.get(`/api/customers/${id}/accounts`)
            this.accounts = data
            this.accounts.forEach((account) => {
                account.label = account.name + " *" + account.accountNumber.slice(5, 10) + " (Balance: $" + account.balance.toFixed(2) + ")"
            })
        },
        async loadUnpaidBills() {
            const {data} = await axios.get(`/api/customers/${this.customerID}/unpaidbills`)
            this.bills = data
            this.accountsLoaded = true

            this.bills.forEach((bill) => {
                bill.title = bill.payeeName + ": Due " + bill.dueDate
            })
            if (this.bill) {
                this.bill.title = this.bill.payeeName + ": Due " + this.bill.dueDate
            }
        },
        payBill() {
            axios.put(`/api/unpaidbill/${this.selectedBill.id}/${this.selectedAccount.accountNumber}/${this.amountToPay}`).then(() => {
                console.log("Bill paid successfully.")
                this.paymentSuccess = true
            }).catch(() => {
                console.log("Bill payment failed.")
                this.paymentFail = true
            })
        }
    },
    beforeMount() {
        this.loadUnpaidBills()
        this.loadAccounts()
    }
}
</script>

<style scoped>

</style>