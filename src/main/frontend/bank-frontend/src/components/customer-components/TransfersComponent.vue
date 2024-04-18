<template>
    <v-card v-if="accountsLoaded">
        <v-card-title style="background-color: #4097f5; color: #ffffff">Transfer Form</v-card-title>
        <v-card-text>
            <v-form class="mt-5">
                <v-select
                        v-model="accountFrom"
                        label="From"
                        :items="accounts"
                        item-title="label"
                        return-object
                        variant="outlined"
                />
                <v-select
                        v-model="accountTo"
                        label="To"
                        :items="accounts"
                        item-title="label"
                        return-object
                        variant="outlined"

                />
                <v-text-field
                        v-model="amount"
                        name="amount"
                        label="Amount"
                        type="number"
                        placeholder="Enter amount to transfer"
                        variant="outlined"
                        prepend-icon="mdi-currency-usd"
                        hide-spin-buttons
                        required
                />
                <v-alert
                    v-model="transferSuccess"
                    text="Transfer successful. You may close this dialog or initiate another transfer."
                    type="success"
                    closable
                    @close="transferSuccess = false"
                />
                <v-alert
                    v-model="transferFail"
                    text="Transfer failed. You may be initiating an illegal transfer"
                    type="error"
                    closable
                    @close="transferFail = false"
                />
                <v-alert
                    v-model="notEnoughFail"
                    type="error"
                    closable
                    @close="notEnoughFail = false"
                >
                    Not enough in {{accountFrom.label}} to initiate transfer.
                </v-alert>
                <v-alert
                    v-if="accountFrom === accountTo"
                    title="Warning"
                    text="You are attempting to transfer from one account to the same account. This transfer will not initiate."
                    type="warning"
                />
                <v-container class="d-flex justify-end">
                    <v-btn color="#4097f5" @click="transferMoney">Submit</v-btn>
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
    name: "TransfersComponent.vue",
    data: () => {
        const store = useCustomerStore()
        const tellerStore = useTellerStore()
        return {
            customerStore: store,
            tellerStore: tellerStore,
            accountsLoaded: false,
            amount: 0,
            accounts: [],
            accountFrom: "",
            accountTo: " ",
            transferSuccess: false,
            transferFail: false,
            notEnoughFail: false,
            sameAccountWarning: false,
        }
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
                account.label = account.name + " *" + account.accountNumber.slice(5, 10)
            })
            this.accountsLoaded = true
        },
        async transferMoney() {
            if (this.accountFrom.balance < this.amount) {
                console.log("Not enough in account " + this.accountFrom.accountNumber + " to initiate transfer")
                this.notEnoughFail = true
            } else if (this.accountFrom === this.accountTo) {
                console.log("Not going to transfer to/from the same account.")
            } else {
                await axios.put(`/api/${this.accountTo.accountNumber}/${this.accountFrom.accountNumber}/${this.amount}`).then(() => {
                    console.log("Transfer successful.")
                    this.transferSuccess = true
                }).catch(() => {
                    console.log("Transfer failed.")
                    this.transferFail = true
                })
            }
        }
    },
    beforeMount() {
        this.loadAccounts()
    }
}
</script>

<style scoped>

</style>