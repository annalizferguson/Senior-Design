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
            <v-row>
                <v-col>
                    <v-container v-if="type === 'Savings' || type === 'Money Market'"><b>Interest Rate:</b></v-container>
                </v-col>
                <v-col>
                    <v-container v-if="type === 'Savings' || type === 'Money Market'">{{ interestRate }}%</v-container>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-container v-if="type === 'Money Market'"><b>Transaction Count:</b></v-container>
                </v-col>
                <v-col>
                    <v-container v-if="type === 'Money Market'">{{ transactionCount }}</v-container>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-container v-if="type === 'Money Market'"><b>Transaction Limit:</b></v-container>
                </v-col>
                <v-col>
                    <v-container v-if="type === 'Money Market'">{{ transactionLimit }}</v-container>
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
    data: function() {
        return {
            number: this.account.number,
            balance: this.account.balance,
            type: this.account.type,
            title: this.account.type + " *" + this.account.number.slice(5, 9),
            interestRate: this.account.interestRate,
            transactionCount: this.account.transactionCount,
            transactionLimit: this.account.transactionLimit
        }
    }
}
</script>

<style scoped>

</style>