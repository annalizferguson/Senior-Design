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
        <v-container class="d-flex justify-center">
            <v-container v-if="accountsLoaded"
                         class="d-flex flex-wrap overflow-y-auto">
                <v-card v-for="(item, index) in accounts" class="mb-5 mr-4 ml-4">
                    <v-card-title style="background-color: #4097f5; color: #ffffff">
                        {{ item.name }} *{{ item.accountNumber.slice(5, 9) }}
                    </v-card-title>
                    <AccountDetailsDialog width="55%" :account="item"/>
                </v-card>
            </v-container>
        </v-container>
    </v-container>
</template>

<script>
import AccountDetailsDialog from "@/components/customer-components/AccountDetailsDialog.vue";
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";
import axios from "axios";
import {useCustomerStore} from "@/states/UserStore.js";

export default {
    name: "CustomerAccountsPage.vue",
    components: {TransfersComponent, AccountDetailsDialog},
    data: function () {
        const store = useCustomerStore()
        return {
            accountsLoaded: false,
            dialogActive: false,
            accounts: [],
            store: store,
        }
    },
    methods: {
        async loadAccounts() {
            const id = this.store.getID
            const {data} = await axios.get(`/api/customers/${id}/accounts`)
            this.accounts = data
            this.accountsLoaded = true
        }
    },
    beforeMount() {
        this.loadAccounts()
    }
}
</script>

<style scoped>

</style>