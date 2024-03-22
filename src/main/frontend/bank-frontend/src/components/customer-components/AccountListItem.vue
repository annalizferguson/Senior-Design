<template>
    <v-card
            class="ml-3 mb-6"
            width= "95%"
            height="95%"
            variant="tonal"
    >
        <v-card-title
                class="d-flex justify-space-between"
                style="font-size: 1em"
        >
            <div>{{ accountType }} *{{ endingDigits }}</div>
            <v-btn
                    color="#4097f5"
                    variant="plain"
                    @click="dialogActive = true"
            >
                <b><u>Quick Peak</u></b>
            </v-btn>
        </v-card-title>
        <v-card-text
                class="d-flex"
        >
            Current Balance...............................
            <div
                    style="font-size: 2.7em"
            >
                ${{ balance }}
            </div>
        </v-card-text>
        <v-dialog
            v-model="dialogActive"
            width="40%"
        >
            <v-card>
                <v-card-title style="background-color: #4097f5; color: #ffffff" class="d-flex justify-space-between align-center">
                    {{ title }}
                    <v-btn icon="mdi-close" color="#4097f5" variant="flat" @click="dialogActive = false"/>
                </v-card-title>
                <AccountDetailsDialog :account="currentAccount"/>
            </v-card>
        </v-dialog>
    </v-card>
</template>

<script>
import testaccount from "@/test-files/testaccount.json"
import AccountDetailsDialog from "@/components/customer-components/AccountDetailsDialog.vue";

export default {
    name: "AccountListItem.vue",
    components: {AccountDetailsDialog},
    props: {
        account: {
            type: Object
        }
    },

    data: function() {
        return {
            currentAccount: this.account,
            dialogActive: false,
            accountName: this.account.name,
            accountType: this.account.type,
            accountNumber: this.account.number,
            balance: this.account.balance,
            endingDigits: this.account.number.slice(5, 9),
            title: this.account.type + " *" + this.account.number.slice(5, 9),
        }
    },


}
</script>

<style scoped>

</style>