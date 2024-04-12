<template>
    <v-card v-if="accountsLoaded">
        <v-card-title style="background-color: #4097f5; color: #ffffff">Open Account for {{ customerFirstName }}
            {{ customerLastName }}
        </v-card-title>
        <v-card-text>
            <v-form class="mt-5">
                <v-select
                        v-model="chosenAccountType"
                        label="Account Type"
                        :items="accountTypes"
                        item-title="label"
                        item-value="type"
                        return-object
                        variant="outlined"
                />
                <v-text-field
                        v-if="chosenAccountType.type === 'savings' || chosenAccountType.type === 'creditcard' || chosenAccountType.type === 'moneymarket'"
                        v-model="interestRate"
                        label="Interest Rate"
                        variant="outlined"
                        type="number"
                        append-icon="mdi-percent-outline"
                        hide-spin-buttons
                />
                <v-text-field
                    v-if="chosenAccountType.type === 'creditcard'"
                    v-model="penaltyInterest"
                    label="Penalty Interest"
                    variant="outlined"
                    type="number"
                    append-icon="mdi-percent-outline"
                    hide-spin-buttons
                />
                <v-text-field
                    v-if="chosenAccountType.type === 'creditcard'"
                    v-model="penaltyFee"
                    label="Penalty Fee"
                    variant="outlined"
                    type="number"
                    prepend-icon="mdi-currency-usd"
                    hide-spin-buttons
                />
                <v-text-field
                        v-if="chosenAccountType.type === 'moneymarket'"
                        v-model="transactionLimit"
                        label="Transaction Limit"
                        variant="outlined"
                        type="number"
                        prepend-icon="mdi-pound"
                        hide-spin-buttons
                />
                <v-text-field
                        v-if="chosenAccountType.type === 'homemortgage'"
                        v-model="monthlyDue"
                        label="Monthly Due"
                        variant="outlined"
                        type="number"
                        prepend-icon="mdi-currency-usd"
                        hide-spin-buttons
                />
                <v-text-field
                        v-if="chosenAccountType.type === 'homemortgage'"
                        v-model="totalMortgage"
                        label="Total Mortgage"
                        variant="outlined"
                        type="number"
                        prepend-icon="mdi-currency-usd"
                        hide-spin-buttons
                />
                <v-container class="d-flex justify-end">
                    <v-btn
                            color="#4097f5"
                            @click="createAccount"
                    >
                        Create
                    </v-btn>
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
    name: "CreateFinancialAccountComponent.vue",
    data: () => {
        const store = useCustomerStore()
        const tellerStore = useTellerStore()
        const accountTypes = [
            {type: "checking", label: "Checking"},
            {type: "savings", label: "Savings"},
            {type: "creditcard", label: "Credit Card"},
            {type: "homemortgage", label: "Home Mortgage"},
            {type: "moneymarket", label: "Money Market"},
        ]
        return {
            customerStore: store,
            tellerStore: tellerStore,
            customerID: tellerStore.getCustomerID,
            accountTypes: accountTypes,
            chosenAccountType: accountTypes[0],
            customerFirstName: "",
            customerLastName: "",
            accountsLoaded: false,
            accounts: [],
            interestRate: 0,
            transactionLimit: 0,
            monthlyDue: 0,
            totalMortgage: 0,
            penaltyInterest: 0,
            penaltyFee: 0,
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
        },
        async loadCustomer() {
            axios.get(`/api/customers/${this.customerID}`).then((response) => {
                this.customerFirstName = response.data.firstName
                this.customerLastName = response.data.lastName
            }).catch(() => {
                console.log("ERROR: Customer not found.")
            })
        },
        async createAccount() {
            let accountInfo = {}
            switch (this.chosenAccountType.type) {
                case "checking":
                    accountInfo = {
                        "customerID": this.customerID,
                        "balance": 0,
                    }
                    break;
                case "savings":
                    accountInfo = {
                        "customerID": this.customerID,
                        "balance": 0,
                        "interestRate": this.interestRate,
                    }
                    break;
                case "creditcard":
                    accountInfo = {
                        "customerID": this.customerID,
                        "balance": 0,
                        "interestRate": this.interestRate,
                        "amountDue": 0,
                        "missedPayments": 0,
                    }
                    break;
                case "homemortgage":
                    accountInfo = {
                        "balance": 0,
                    }
                    break;
                case "moneymarket":
                    accountInfo = {
                        "balance": 0,
                    }
                    break;
            }
            axios.post(`/api/${this.chosenAccountType.type}`, accountInfo).then((response) => {
                console.log("Account creation successful.")
            }).catch((e) => {
                console.log("Account creation failed.")
            })
        }
    },
    beforeMount() {
        this.loadAccounts()
        this.loadCustomer()
    }
}
</script>

<style scoped>

</style>