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
            accountTo: "",
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
            await axios.put(`/api/${this.accountTo.id}/${this.accountFrom.id}/${this.amount}`).then(() => {
                console.log("Transfer successful.")
            }).catch(() => {
                console.log("Transfer failed.")
            })
        }
    },
    beforeMount() {
        this.loadAccounts()
    }
}
</script>

<style scoped>

</style>