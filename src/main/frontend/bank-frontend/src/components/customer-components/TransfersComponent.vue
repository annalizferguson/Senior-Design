<template>
    <v-card>
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
import testAccounts from "@/test-files/testAccounts.json";
export default {
    name: "TransfersComponent.vue",
    data: () => {
        let accounts = testAccounts
        accounts.forEach((account) => {
             account.label = account.type + " *" + account.number.slice(5, 9)
        })
        return {
            amount: 0,
            accounts: accounts,
            accountFrom: {},
            accountTo: {},
        }
    }
}
</script>

<style scoped>

</style>