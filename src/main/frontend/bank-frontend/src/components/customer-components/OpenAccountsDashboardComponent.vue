<template>
    <v-card
            width="50%"
            height="85%"
            variant="outlined"
            class="mr-6"
    >
        <v-card-title
                class="d-flex justify-space-between"
        >
            Open Accounts
            <div>
                <v-btn
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
                    <TransfersComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="dialogActive = false"
                    >
                        Cancel
                    </v-btn>
                </v-dialog>
                <v-btn
                        variant="plain"
                        color="#4097f5"
                        style="font-size:0.65em"
                        to="/accounts"
                >
                    See all accounts
                </v-btn>
            </div>
        </v-card-title>
        <v-container v-if=accountsLoaded class="d-flex flex-wrap justify-center overflow-x-auto">
            <AccountListItem
                    width="60%"
                    v-for="(item, index) in accounts"
                    :account="item"/>
        </v-container>
    </v-card>
</template>

<script>
import AccountListItem from "@/components/customer-components/AccountListItem.vue";
import testAccounts from "@/test-files/testAccounts.json";
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";
import axios from 'axios';
import {useCustomerStore} from "@/states/UserStore.js";

export default {
    name: "OpenAccountsDashboardComponent.vue",
    components: {AccountListItem, TransfersComponent},
    data: () => {
        const store = useCustomerStore()
        return {
            accountsLoaded: false,
            accounts: testAccounts,
            dialogActive: false,
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