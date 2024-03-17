<template>
    <v-card
            width="40%"
            height="85%"
            variant="outlined"
            class="mr-6 overflow-y-auto"
    >
        <v-card-title
                class="d-flex justify-space-between"
        >
            Open Accounts
            <v-btn
                to="/customer-dash"
                variant="plain"
                color="#4097f5"
                style="font-size:0.60em"
            >
                Make a Transfer
            </v-btn>
            <v-btn
                    to="/customer-dash"
                    variant="plain"
                    color="#4097f5"
                    style="font-size:0.60em"
            >
                See all accounts
            </v-btn>
        </v-card-title>
        <v-card
            variant="flat"
            height="20%"
            v-for="(item, index) in accounts"
        >
            <AccountListItem :account="item"/>
        </v-card>
    </v-card>
</template>

<script>
import AccountListItem from "@/components/customer-components/AccountListItem.vue";
import testAccounts from "@/test-files/testAccounts.json";

export default {
    name: "OpenAccountsDashboardComponent.vue",
    components: {AccountListItem},
    data: () => {
        return {
            accounts: testAccounts
        }
    }
}
</script>

<style scoped>

</style>