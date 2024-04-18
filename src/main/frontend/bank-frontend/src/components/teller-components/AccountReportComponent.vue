<template>
    <v-data-table
            v-if="transactionsLoaded && transactions.length > 0"
            class="ml-5"
            :headers="header"
            :items="transactions"
            height="50vh"
    />
</template>

<script>
import axios from 'axios'

export default {
    name: "AccountReportComponent.vue",
    props: {
        accountNumber: {
            type: String
        }
    },
    data: () => {
        return {
            transactions: [],
            transactionsLoaded: false,
            header: [
                {
                    title: 'ID',
                    align: 'start',
                    sortable: false,
                    key: 'id',
                },
                {title: 'Account #', key: 'accountNumber'},
                {title: 'Type', key: 'type'},
                {title: 'Amount', key: 'amount'},
                {title: 'Date', key: 'date', sortable: false},
                {title: 'Payee', key: 'payeeName'},
            ],
        }
    },
    methods: {
        async getTransactions() {
            await axios.get(`/api/transactions/${this.accountNumber}`).then((response) => {
                this.transactions = response.data
                console.log(response)
                this.transactionsLoaded = true
                console.log("Successfully loaded transactions for account " + this.accountNumber)
            }).catch(() => {
                console.log("Error loading transactions for account " + this.accountNumber)
            })
        }
    },
    beforeMount() {
        this.getTransactions()
    }
}
</script>

<style scoped>

</style>