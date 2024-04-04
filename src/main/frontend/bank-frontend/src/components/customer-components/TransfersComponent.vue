<template>
    <v-card v-if="accountsLoaded">
        <v-card-title style="background-color: #4097f5; color: #ffffff">Transfer Form</v-card-title>
        <v-card-text>
            <v-form class="mt-5">
                <v-select
                        label="From"
                        :items="accounts"
                        item-title="label"
                        return-object
                        variant="outlined"
                />
                <v-select
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

export default {
    name: "TransfersComponent.vue",
    data: () => {
        const store = useCustomerStore()
        return {
            store: store,
            accountsLoaded: false,
            amount: 0,
            accounts: [],
            accountFrom: {},
            accountTo: {},
        }
    },
    methods: {
        async loadAccounts() {
            const id = this.store.getID
            const {data} = await axios.get(`/api/customers/${id}/accounts`)
            this.accounts = data
            this.accounts.forEach((account) => {
                account.label = account.type + " *" + account.accountNumber.slice(6, 10)
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