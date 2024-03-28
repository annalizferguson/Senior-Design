<template>
    <v-card>
        <v-card-title style="background-color: #4097f5; color: #ffffff">
            {{ title }}
        </v-card-title>
        <v-card-text>
            <v-row>
                <v-col>
                    <v-container><b>Account Number:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>{{ number }}</v-container>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-container><b>Balance:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>${{ balance }}</v-container>
                </v-col>
            </v-row>
            <v-row v-if="type === 'Savings' || type === 'Money Market' || type === 'Credit Card' ">
                <v-col>
                    <v-container><b>Interest
                        Rate:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>
                        {{ interestRate }}%
                    </v-container>
                </v-col>
            </v-row>
            <v-row v-if="type === 'Money Market'">
                <v-col>
                    <v-container><b>Transaction Count:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>{{ transactionCount }}</v-container>
                </v-col>
            </v-row>
            <v-row v-if="type === 'Money Market'">
                <v-col>
                    <v-container><b>Transaction Limit:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>{{ transactionLimit }}</v-container>
                </v-col>
            </v-row>
            <v-row v-if="type === 'Credit Card'">
                <v-col>
                    <v-container><b>Amount Due:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>${{ amountDue }}</v-container>
                </v-col>
            </v-row>
            <v-row v-if="type === 'Home Mortgage'">
                <v-col>
                    <v-container><b>Monthly Amount Due:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>${{ monthlyDue }}</v-container>
                </v-col>
            </v-row>
            <v-row v-if="type === 'Home Mortgage'">
                <v-col>
                    <v-container><b>Total Mortgage:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>${{ totalMortgage }}</v-container>
                </v-col>
            </v-row>
            <v-row v-if="type === 'Home Mortgage'">
                <v-col>
                    <v-container><b>Mortgage Left:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>${{ mortgageLeft }}</v-container>
                </v-col>
            </v-row>
            <v-row v-if="type === 'Home Mortgage'">
                <v-col>
                    <v-container><b>Unpaid Balance:</b></v-container>
                </v-col>
                <v-col>
                    <v-container>${{ unpaidBalance }}</v-container>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>

<script>
import testAccounts from "@/test-files/testAccounts.json"

export default {
    name: "AccountDetailsDialog.vue",
    props: {
        account: {
            type: Object
        }
    },
    data: function () {
        return {
            number: this.account.number,
            balance: this.account.balance,
            type: this.account.type,
            title: this.account.type + " *" + this.account.number.slice(5, 9),
            interestRate: this.account.interestRate,
            transactionCount: this.account.transactionCount,
            transactionLimit: this.account.transactionLimit,
            amountDue: this.account.amountDue,
            totalMortgage: this.account.totalMortgage?.toLocaleString(),
            mortgageLeft: this.account.mortgageLeft?.toLocaleString(),
            monthlyDue: this.account.monthlyDue,
            unpaidBalance: this.account.unpaidBalance
        }
    },
}
</script>

<style scoped>

</style>