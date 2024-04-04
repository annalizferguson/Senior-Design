<template>
    <v-container>
        <v-card height="10%" class="mb-4" style="background-color: #1565c0; color: white">
            <v-card-title class="d-flex justify-space-between">
                All Upcoming Payments
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
                            to="/customer-dash"
                    >
                        Return to Dashboard
                    </v-btn>
                    <v-dialog
                            v-model="dialogActive"
                            width="50%"
                    >
                        <MakeAPaymentComponent/>
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
        <v-container v-if="billsLoaded" style="height: calc(100vh - 165px)" class="d-flex justify-center flex-wrap overflow-y-auto">
            <UpcomingPaymentsItem height="50%" width="45%" class="mb-4 mr-4" v-for="(item, index) in bills" :bill="item"/>
        </v-container>
    </v-container>
</template>

<script>
import UpcomingPaymentsItem from "@/components/customer-components/UpcomingPaymentsItem.vue";
import MakeAPaymentComponent from "@/components/customer-components/MakeAPaymentComponent.vue";
import axios from "axios";
import {useCustomerStore} from "@/states/UserStore.js";

export default {
    name: "CustomerBillsPage.vue",
    components: {MakeAPaymentComponent, UpcomingPaymentsItem},
    data: () => {
        const store = useCustomerStore()
        return {
            billsLoaded: false,
            dialogActive: false,
            bills: [],
            store: store,
        }
    },
    methods: {
        async loadUnpaidBills() {
            const id = this.store.getID
            const {data} = await axios.get(`/api/customers/${id}/unpaidbill`)
            console.log(data)
            this.bills = [data]
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