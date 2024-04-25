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
                        persistent
                >
                    <TransfersComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="transfersDialogActive = false; loadCustomerAccounts()"
                    >
                        Close
                    </v-btn>
                </v-dialog>
                <v-dialog
                        v-model="depositsDialogActive"
                        width="50%"
                        persistent
                >
                    <DepositsComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="depositsDialogActive = false; loadCustomerAccounts()"
                    >
                        Close
                    </v-btn>
                </v-dialog>
                <v-dialog
                        v-model="withdrawalsDialogActive"
                        width="50%"
                        persistent
                >
                    <WithdrawalsComponent/>
                    <v-btn
                            color="#4097f5"
                            @click="withdrawalsDialogActive = false; loadCustomerAccounts()"
                    >
                        Close
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
        <v-container v-if="closeSuccess || closeFail || closeWarning">
            <v-alert
                    v-model="closeSuccess"
                    type="success"
            >
                Successfully closed {{ deleteAccountName }}.
            </v-alert>
            <v-alert
                    v-model="closeFail"
                    type="error"
            >
                Unable to close {{ deleteAccountName }}.
            </v-alert>
            <v-alert
                v-model="closeWarning"
                type="warning"
                closable
                @close="closeWarning = false"
            >
                Cannot close {{ deleteAccountName }} unless the balance is $0.
            </v-alert>
        </v-container>
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
                        <v-tooltip text="Close Account">
                            <template v-slot:activator="{ props }">
                                <v-btn v-bind="props" icon="mdi-delete-outline" variant="plain"
                                       @click="deleteAccount(index)"/>
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
            customerFirstName: store.getCustomerFirstName,
            customerLastName: store.getCustomerLastName,
            closeSuccess: false,
            closeFail: false,
            closeWarning: false,
            deleteAccountName: ""
        }
    },
    methods: {
        async loadCustomerAccounts() {
            const {data} = await axios.get(`/api/customers/${this.customerID}/accounts`)
            this.accounts = data
            this.accountsLoaded = true
        },
        async deleteAccount(index) {
            const accountToDelete = this.accounts[index]
            if (accountToDelete.balance !== 0) {
                this.deleteAccountName = accountToDelete.name + " *" + accountToDelete.accountNumber.slice(5, 9)
                this.closeWarning = true
            } else {
                let accountType = ''
                switch (accountToDelete.name) {
                    case "Checking Account":
                        accountType = "checking"
                        break
                    case "Savings Account":
                        accountType = "savings"
                        break
                    case "Credit Card":
                        accountType = "creditcard"
                        break
                    case "Money Market":
                        accountType = "moneymarket"
                        break
                    case "Home Mortgage":
                        accountType = "homemortgage"
                        break
                }
                axios.delete(`/api/${accountType}/${accountToDelete.id}`).then(() => {
                    console.log("Account closed successfully.")
                    this.deleteAccountName = accountToDelete.name + " *" + accountToDelete.accountNumber.slice(5, 9)
                    this.closeSuccess = true
                    setTimeout(() => {
                        this.closeSuccess = false
                    }, 3000)
                    this.loadCustomerAccounts()
                }).catch(() => {
                    console.log("Error closing account.")
                    this.deleteAccountName = accountToDelete.name + " *" + accountToDelete.accountNumber.slice(5, 9)
                    this.closeFail = true
                    setTimeout(() => {
                        this.closeFail = false
                    }, 3000)
                })
            }
        }
    },
    beforeMount() {
        this.loadCustomerAccounts()
    }
}
</script>

<style scoped>

</style>