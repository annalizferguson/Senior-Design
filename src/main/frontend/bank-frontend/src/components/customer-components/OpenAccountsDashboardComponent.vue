<template>
    <v-card
            width="50%"
            height="85%"
            variant="outlined"
            class="mr-6 overflow-y-auto"
    >
        <v-card-title
                class="d-flex justify-space-between"
        >
            Open Accounts
            <div>
                <v-btn
                        to="/customer-dash"
                        variant="plain"
                        color="#4097f5"
                        style="font-size:0.65em"
                        @click="dialogActive = true"
                >
                    Make a Transfer
                </v-btn>
                <v-dialog
                    v-model="dialogActive"
                    width="50%"
                >
                    <TransfersComponent />
                    <v-btn
                        color="#4097f5"
                        @click="dialogActive = false"
                    >
                        Cancel
                    </v-btn>
                </v-dialog>
                <v-btn
                        to="/customer-dash"
                        variant="plain"
                        color="#4097f5"
                        style="font-size:0.65em"
                >
                    See all accounts
                </v-btn>
            </div>
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
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";

export default {
    name: "OpenAccountsDashboardComponent.vue",
    components: {AccountListItem, TransfersComponent},
    data: () => {
        return {
            accounts: testAccounts,
            dialogActive: false,
        }
    }
}
</script>

<style scoped>

</style>