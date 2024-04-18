<template>
    <v-container>
        <v-card height="10%" class="mb-4" style="background-color: #1565c0; color: white">
            <v-card-title class="d-flex justify-space-between">
                <div>All {{ billsType }} Bills for <b>{{ customerFirstName }} {{ customerLastName }}</b></div>
                <v-btn
                        variant="plain"
                        color="white"
                        style="font-size:0.65em"
                        @click="switchCurrentType"
                >
                    Show {{ otherBillsType }}
                </v-btn>
                <div>
                    <v-btn
                            v-if="showUnpaidBills"
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
                            persistent
                    >
                        <MakeAPaymentComponent :customerID="customerID"/>
                        <v-btn
                                color="#4097f5"
                                @click="dialogActive = false; loadUnpaidBills(); loadPaidBills()"
                        >
                            Cancel
                        </v-btn>
                    </v-dialog>
                </div>
            </v-card-title>
        </v-card>
        <v-container v-if="billsLoaded && showUnpaidBills" style="height: calc(100vh - 165px)"
                     class="d-flex justify-center flex-wrap overflow-y-auto">
            <UpcomingPaymentsItem height="50%" width="45%" class="mb-4 mr-4" v-for="(item, index) in unpaidBills"
                                  :bill="item" :customerID="customerID"/>
        </v-container>
        <v-container v-if="billsLoaded && !showUnpaidBills" style="height: calc(100vh - 165px)"
                     class="d-flex justify-center flex-wrap overflow-y-auto">
            <PaidBillItem height="50%" width="45%" class="mb-4 mr-4" v-for="(item, index) in paidBills"
                          :bill="item" :customerID="customerID"/>
        </v-container>
    </v-container>
</template>

<script>
import UpcomingPaymentsItem from "@/components/customer-components/UpcomingPaymentsItem.vue";
import PaidBillItem from "@/components/customer-components/PaidBillItem.vue";
import MakeAPaymentComponent from "@/components/customer-components/MakeAPaymentComponent.vue";
import {useTellerStore} from "@/states/TellerStore.js";
import axios from "axios";

export default {
    name: "CustomerBillsComponent.vue",
    components: {MakeAPaymentComponent, UpcomingPaymentsItem, PaidBillItem},
    data: () => {
        const tellerStore = useTellerStore()
        return {
            tellerStore: tellerStore,
            customerID: tellerStore.getCustomerID,
            customerFirstName: tellerStore.getCustomerFirstName,
            customerLastName: tellerStore.getCustomerLastName,
            unpaidBills: [],
            paidBills: [],
            billsLoaded: false,
            dialogActive: false,
            billsType: "Unpaid",
            otherBillsType: "Paid",
            showUnpaidBills: true,
        }
    },
    methods: {
        async loadUnpaidBills() {
            const {data} = await axios.get(`/api/customers/${this.customerID}/unpaidbills`)
            this.unpaidBills = data
            this.billsLoaded = true
        },
        async loadPaidBills() {
            const {data} = await axios.get(`/api/customers/${this.customerID}/paidbills`)
            this.paidBills = data
        },
        switchCurrentType() {
            this.showUnpaidBills = !this.showUnpaidBills
            if (this.showUnpaidBills) {
                this.billsType = "Unpaid"
                this.otherBillsType = "Paid"
            } else {
                this.billsType = "Paid"
                this.otherBillsType = "Unpaid"
            }
        }
    },
    beforeMount() {
        this.loadUnpaidBills()
        this.loadPaidBills()
    }
}
</script>

<style scoped>

</style>