<template>
    <v-container>
        <v-card height="10%" class="mb-4">
            <v-card-title class="d-flex justify-space-between" style="background-color: #1565c0; color: white">
                <div> Transaction Reports for
                    <b>{{ customerFirstName }} {{ customerLastName }}</b>
                </div>
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
            </v-card-title>
        </v-card>
        <v-container class="d-flex justify-center">
            <v-container v-if="accountsLoaded"
                         class="d-flex flex-wrap overflow-y-auto justify-center"
                         style="height: calc(100vh - 250px)"
                         :key="accounts"
            >
                <v-card width="100%" v-for="(item, index) in accounts" class="mb-5 mr-4 ml-4">
                    <v-card-title style="background-color: #4097f5; color: #ffffff"
                                  class="d-flex justify-space-between align-center">
                        {{ item.name }} *{{ item.accountNumber.slice(5, 9) }}
                    </v-card-title>
                    <AccountReportComponent width="60%" :accountNumber="item.accountNumber"/>
                </v-card>
            </v-container>
        </v-container>
    </v-container>
</template>

<script>
import {useTellerStore} from "@/states/TellerStore.js";
import axios from "axios";
import AccountReportComponent from "@/components/teller-components/AccountReportComponent.vue";

export default {
    name: "CustomerReportComponent.vue",
    components: {AccountReportComponent},
    data: () => {
        const tellerStore = useTellerStore()
        return {
            tellerStore: tellerStore,
            customerID: tellerStore.getCustomerID,
            customerFirstName: tellerStore.getCustomerFirstName,
            customerLastName: tellerStore.getCustomerLastName,
            accounts: [],
            accountsLoaded: false,
        }
    },
    methods: {
        async loadCustomerAccounts() {
            const {data} = await axios.get(`/api/customers/${this.customerID}/accounts`)
            this.accounts = data
            this.accountsLoaded = true
        },
    },
    beforeMount() {
        this.loadCustomerAccounts()
    }
}
</script>

<style scoped>

</style>