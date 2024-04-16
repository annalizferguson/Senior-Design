<template>
    <v-container>
        <v-card height="10%" class="mb-4">
            <v-card-title class="d-flex justify-space-between" style="background-color: #1565c0; color: white">
                <div> Open Accounts for
                    <b>{{ customerFirstName }} {{ customerLastName }}</b></div>
                <div>
                    <v-btn
                            variant="plain"
                            color="white"
                            style="font-size:0.65em"
                            to="/teller-dash"
                    >
                        Return to Dashboard
                    </v-btn>
                </div>
                <v-dialog
                        v-model="transfersDialogActive"
                        width="50%"
                >
                    <TransfersComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="transfersDialogActive = false; loadCustomerAccounts()"
                    >
                        Cancel
                    </v-btn>
                </v-dialog>
                <v-dialog
                        v-model="depositsDialogActive"
                        width="50%"
                >
                    <DepositsComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="depositsDialogActive = false; loadCustomerAccounts()"
                    >
                        Cancel
                    </v-btn>
                </v-dialog>
                <v-dialog
                        v-model="withdrawalsDialogActive"
                        width="50%"
                >
                    <WithdrawalsComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="withdrawalsDialogActive = false; loadCustomerAccounts()"
                    >
                        Cancel
                    </v-btn>
                </v-dialog>
                <v-dialog
                        v-model="createAccountDialogActive"
                        width="50%"
                        persistent
                >
                    <CreateFinancialAccountComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="createAccountDialogActive = false; loadCustomerAccounts()"
                    >
                        Close
                    </v-btn>
                </v-dialog>
            </v-card-title>
            <v-card-actions class="d-flex justify-center">
                <v-btn
                        variant="plain"
                        color="#1565c0"
                        style="font-size:0.85em"
                        @click="createAccountDialogActive = true"
                >
                    Open New Financial Account
                </v-btn>
                <v-btn
                        variant="plain"
                        color="#1565c0"
                        style="font-size:0.85em"
                        @click="transfersDialogActive = true"
                >
                    Make a Transfer
                </v-btn>
                <v-btn
                        variant="plain"
                        color="#1565c0"
                        style="font-size:0.85em"
                        @click="depositsDialogActive = true"
                >
                    Make a Deposit
                </v-btn>
                <v-btn
                        variant="plain"
                        color="#1565c0"
                        style="font-size:0.85em"
                        @click="withdrawalsDialogActive = true"
                >
                    Make a Withdrawal
                </v-btn>
            </v-card-actions>
        </v-card>
        <v-container class="d-flex justify-center">
            <v-container v-if="accountsLoaded"
                         class="d-flex flex-wrap overflow-y-auto justify-center"
                         style="height: calc(100vh - 250px)"
                         :key="accounts"
            >
                <v-card width="30%" v-for="(item, index) in accounts" class="mb-5 mr-4 ml-4">
                    <v-card-title style="background-color: #4097f5; color: #ffffff"
                                  class="d-flex justify-space-between align-center">
                        {{ item.name }} *{{ item.accountNumber.slice(5, 9) }}
                        <v-tooltip text="Delete Account">
                            <template v-slot:activator="{ props }">
                                <v-btn v-bind="props" icon="mdi-delete-outline" variant="plain"/>
                            </template>
                        </v-tooltip>
                    </v-card-title>
                    <AccountDetailsDialog width="60%" :account="item"/>
                </v-card>
            </v-container>
        </v-container>
    </v-container>
</template>

<script>
import axios from 'axios'
import {useTellerStore} from "@/states/TellerStore.js";
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";
import AccountDetailsDialog from "@/components/customer-components/AccountDetailsDialog.vue";
import DepositsComponent from "@/components/teller-components/DepositsComponent.vue";
import WithdrawalsComponent from "@/components/teller-components/WithdrawalsComponent.vue";
import CreateFinancialAccountComponent from "@/components/teller-components/CreateFinancialAccountComponent.vue";

export default {
    name: "CustomerAccountsComponent.vue",
    components: {
        AccountDetailsDialog,
        TransfersComponent,
        DepositsComponent,
        WithdrawalsComponent,
        CreateFinancialAccountComponent
    },
    data: () => {
        const store = useTellerStore()
        return {
            store: store,
            customerID: store.getCustomerID,
            accounts: [],
            accountsLoaded: false,
            transfersDialogActive: false,
            depositsDialogActive: false,
            withdrawalsDialogActive: false,
            createAccountDialogActive: false,
            customerFirstName: "",
            customerLastName: ""
        }
    },
    methods: {
        async loadCustomerAccounts() {
            const {data} = await axios.get(`/api/customers/${this.customerID}/accounts`)
            this.accounts = data
            this.accountsLoaded = true
        },
        async loadCustomer() {
            axios.get(`/api/customers/${this.customerID}`).then((response) => {
                this.customerFirstName = response.data.firstName
                this.customerLastName = response.data.lastName
            }).catch(() => {
                console.log("ERROR: Customer not found.")
            })
        }
    },
    beforeMount() {
        this.loadCustomerAccounts()
        this.loadCustomer()
    }
}
</script>

<style scoped>

</style>