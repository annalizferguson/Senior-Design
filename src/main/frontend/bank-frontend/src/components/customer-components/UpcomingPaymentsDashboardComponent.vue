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
                    <MakeAPaymentComponent/>
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
        <v-container
                class="d-flex flex-wrap flex-row justify-center overflow-x-auto"
        >
            <UpcomingPaymentsItem
                    width="45%"
                    height="90%"
                    class="mr-2 mb-2"
                    v-for="i in 2"
                    :bill="bills[i]"/>
        </v-container>
    </v-card>
</template>

<script>
import UpcomingPaymentsItem from "@/components/customer-components/UpcomingPaymentsItem.vue";
import testPayments from "@/test-files/testPayments.json"
import TransfersComponent from "@/components/customer-components/TransfersComponent.vue";
import MakeAPaymentComponent from "@/components/customer-components/MakeAPaymentComponent.vue";

export default {
    name: "UpcomingPaymentsDashboardComponent.vue",
    components: {MakeAPaymentComponent, TransfersComponent, UpcomingPaymentsItem},
    data: () => {
        return {
            dialogActive: false,
            bills: testPayments
        }
    }
}
</script>

<style scoped>

</style>