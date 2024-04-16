<template>
    <v-card
            width="100%"
            height="65%"
            variant="outlined"
    >
        <v-card-title
                class="d-flex justify-space-between"
        >
            Upcoming Payments
            <div>
                <v-btn
                        variant="plain"
                        color="#4097f5"
                        style="font-size:0.65em"
                        @click="dialogActive = true"
                >
                    Make a payment
                </v-btn>
                <v-dialog
                        v-model="dialogActive"
                        width="50%"
                >
                    <MakeAPaymentComponent :customerID="store.getID"/>
                    <v-btn
                            color="#4097f5"
                            @click="dialogActive = false"
                    >
                        Cancel
                    </v-btn>
                </v-dialog>
                <v-btn
                        to="/bills"
                        variant="plain"
                        color="#4097f5"
                        style="font-size:0.65em"
                >
                    See all
                </v-btn>
            </div>
        </v-card-title>
        <v-container v-if="!billsLoaded">
            <v-alert
                title="No bills to display"
                type="warning"
            />
        </v-container>
        <v-container
                v-if="billsLoaded"
                class="d-flex flex-wrap flex-row justify-center overflow-x-auto"
        >
            <UpcomingPaymentsItem
                    width="45%"
                    height="90%"
                    class="mr-2 mb-2"
                    v-for="(item, index) in bills"
                    :bill="item"
                    :customerID="store.getID"
            />
        </v-container>
    </v-card>
</template>

<script>
import UpcomingPaymentsItem from "@/components/customer-components/UpcomingPaymentsItem.vue";
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";
import MakeAPaymentComponent from "@/components/customer-components/MakeAPaymentComponent.vue";
import axios from 'axios';
import {useCustomerStore} from "@/states/UserStore.js";

export default {
    name: "UpcomingPaymentsDashboardComponent.vue",
    components: {MakeAPaymentComponent, TransfersComponent, UpcomingPaymentsItem},
    data: () => {
        const store = useCustomerStore()
        return {
            billsLoaded: false,
            store: store,
            dialogActive: false,
            bills: []
        }
    },
    methods: {
        async loadUnpaidBills() {
            const id = this.store.getID
            const {data} = await axios.get(`/api/customers/${id}/unpaidbill`)
            console.log(data)
            this.bills = data
            if (this.bills.length > 0) {
                this.billsLoaded = true
            }
        }
    },
    beforeMount() {
        this.loadUnpaidBills()
    }
}
</script>

<style scoped>

</style>