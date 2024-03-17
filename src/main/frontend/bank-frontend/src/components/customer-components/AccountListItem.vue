<template>
    <v-card
            class="d-flex flex-wrap ml-3 mb-5"
            width="95%"
            height="90%"
            variant="outlined"
    >
        <v-card-title
                class="d-flex justify-space-between"
                style="font-size: 1em; width: 480px"
        >
            <div>{{ accountType }} *{{ endingDigits }}</div>
            <v-btn
                    color="#4097f5"
                    variant="plain"
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
    </v-card>
</template>

<script>
import testaccount from "@/test-files/testaccount.json"

export default {
    name: "AccountListItem.vue",
    props: {
        account: {
            type: Object
        }
    },

    data: function() {
        return {
            accountName: this.account.name,
            accountType: this.account.type,
            accountNumber: this.account.number,
            balance: this.account.balance,
            endingDigits: this.account.number.slice(5, 9),
        }
    },


}
</script>

<style scoped>

</style>