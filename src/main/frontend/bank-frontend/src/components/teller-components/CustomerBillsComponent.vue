<template>
    <v-container>
        <v-card height="10%" class="mb-4" style="background-color: #1565c0; color: white">
            <v-card-title class="d-flex justify-space-between">
                <div>All Upcoming Bills for <b>{{ customerFirstName }} {{ customerLastName }}</b></div>
                <div>
                    <v-btn
                            variant="plain"
                            color="white"
                            style="font-size:0.65em"
                            @click="dialogActive = true"
                    >
                        Make a Payment
                    </v-btn>
                    <v-btn
                            variant="plain"
                            color="white"
                            style="font-size:0.65em"
                            to="/teller-dash"
                    >
                        Return to Dashboard
                    </v-btn>
                    <v-dialog
                            v-model="dialogActive"
                            width="50%"
                    >
                        Make a Payment Goes Here
                        <v-btn
                                color="#4097f5"
                                @click="dialogActive = false"
                        >
                            Cancel
                        </v-btn>
                    </v-dialog>
                </div>
            </v-card-title>
        </v-card>
        <v-container v-if="bills.length === 0" class="d-flex justify-center">
            No bills found.
        </v-container>
        <v-container v-if="billsLoaded" style="height: calc(100vh - 165px)"
                     class="d-flex justify-center flex-wrap overflow-y-auto">
            <UpcomingPaymentsItem height="50%" width="45%" class="mb-4 mr-4" v-for="(item, index) in bills"
                                  :bill="item"/>
        </v-container>
    </v-container>
</template>

<script>
import UpcomingPaymentsItem from "@/components/customer-components/UpcomingPaymentsItem.vue";
import MakeAPaymentComponent from "@/components/customer-components/MakeAPaymentComponent.vue";
import {useTellerStore} from "@/states/TellerStore.js";
import axios from "axios";

export default {
    name: "CustomerBillsComponent.vue",
    components: {MakeAPaymentComponent, UpcomingPaymentsItem},
    data: () => {
        const tellerStore = useTellerStore()
        return {
            tellerStore: tellerStore,
            customerID: tellerStore.getCustomerID,
            customerFirstName: tellerStore.getCustomerFirstName,
            customerLastName: tellerStore.getCustomerLastName,
            bills: [],
            billsLoaded: false,
            dialogActive: false,
        }
    },
    methods: {
        async loadUnpaidBills() {
            const {data} = await axios.get(`/api/customers/${this.customerID}/unpaidbill`)
            this.bills = data
            this.billsLoaded = true
        }
    },
    beforeMount() {
        this.loadUnpaidBills()
    }
}
</script>

<style scoped>

</style>