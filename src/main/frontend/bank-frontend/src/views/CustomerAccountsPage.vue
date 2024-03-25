<template>
    <v-container>
        <v-card height="10%" class="mb-4" style="background-color: #1565c0; color: white">
            <v-card-title class="d-flex justify-space-between">
                Open Accounts
                <div>
                    <v-btn
                            variant="plain"
                            color="white"
                            style="font-size:0.65em"
                            @click="dialogActive = true"
                    >
                        Make a Transfer
                    </v-btn>
                    <v-btn
                            variant="plain"
                            color="white"
                            style="font-size:0.65em"
                            to="/customer-dash"
                    >
                        Return to Dashboard
                    </v-btn>
                </div>
                <v-dialog
                        v-model="dialogActive"
                        width="50%"
                >
                    <TransfersComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="dialogActive = false"
                    >
                        Cancel
                    </v-btn>
                </v-dialog>
            </v-card-title>
        </v-card>
        <v-container style="height: calc(100vh - 165px)" class="d-flex justify-center flex-wrap overflow-y-auto">
            <AccountDetailsDialog width="45%" class="mb-4 mr-4" v-for="(item, index) in accounts" :account="item"/>
        </v-container>
    </v-container>
</template>

<script>
import AccountDetailsDialog from "@/components/customer-components/AccountDetailsDialog.vue";
import testAccounts from "@/test-files/testAccounts.json";
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";

export default {
    name: "CustomerAccountsPage.vue",
    components: {TransfersComponent, AccountDetailsDialog},
    data: function () {
        return {
            dialogActive: false,
            accounts: testAccounts
        }
    }
}
</script>

<style scoped>

</style>