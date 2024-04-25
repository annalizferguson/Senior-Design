<template>
    <v-card v-if="accountsLoaded">
        <v-card-title style="background-color: #4097f5; color: #ffffff">Deposit</v-card-title>
        <v-card-text>
            <v-form class="mt-5">
                <v-select
                    v-model="selectedAccount"
                        label="Account Into"
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
                    v-model="depositSuccess"
                    text="Deposit successful. You may close this dialog or initiate another deposit."
                    type="success"
                    @close="depositSuccess = false"
                    closable
                />
                <v-alert
                    v-model="depositFail"
                    text="Deposit failed."
                    type="error"
                    @close="depositFail = false"
                    closable
                />
                <v-container class="d-flex justify-end">
                    <v-btn color="#4097f5" @click="deposit">Submit</v-btn>
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
    name: "DepositsComponent.vue",
    data: () => {
        const store = useCustomerStore()
        const tellerStore = useTellerStore()
        return {
            customerStore: store,
            tellerStore: tellerStore,
            accountsLoaded: false,
            amount: 0,
            accounts: [],
            accountTo: {},
            selectedAccount: "",
            depositSuccess: false,
            depositFail: false,
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
        deposit() {
            axios.post(`/api/deposit/${this.selectedAccount.accountNumber}/${this.amount}`).then(() => {
                console.log("Deposit Successful")
                this.depositSuccess = true
            }).catch(() => {
                console.log("Deposit failed.")
                this.depositFail = true
            })
        },
    },
    beforeMount() {
        this.loadAccounts()
    }
}
</script>

<style scoped>

</style>