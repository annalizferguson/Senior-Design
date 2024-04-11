<template>
    <v-card v-if="accountsLoaded">
        <v-card-title style="background-color: #4097f5; color: #ffffff">Withdrawal</v-card-title>
        <v-card-text>
            <v-form class="mt-5">
                <v-select
                        label="Account From"
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
                <v-container class="d-flex justify-end">
                    <v-btn color="#4097f5">Submit</v-btn>
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
    name: "WithdrawalsComponent.vue",
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
                account.label = account.type + " *" + account.accountNumber.slice(5, 10)
            })
            this.accountsLoaded = true
        }
    },
    beforeMount() {
        this.loadAccounts()
    }
}
</script>

<style scoped>

</style>