<template>
    <v-card
            width="100%"
            height="50%"
            variant="outlined"
    >
        <v-card-title
                class="d-flex justify-space-between"
        >
            Upcoming Payments
            <div>
                <v-btn
                        to="/"
                        variant="plain"
                        color="#4097f5"
                        style="font-size:0.65em"
                >
                    Make a payment
                </v-btn>
                <v-btn
                        to="/"
                        variant="plain"
                        color="#4097f5"
                        style="font-size:0.65em"
                >
                    See all
                </v-btn>
            </div>
        </v-card-title>
        <v-card-text>
            <v-card
                    variant="flat"
                    width="100%"
                    height="60%"
                    class="d-flex justify-center align-center"
            >
                <v-container
                        v-for="(bill, index) in bills"
                >
                    <UpcomingPaymentsItem :bill="bill" />
                </v-container>
            </v-card>
        </v-card-text>
    </v-card>
</template>

<script>
import UpcomingPaymentsItem from "@/components/customer-components/UpcomingPaymentsItem.vue";
import testPayments from "@/test-files/testPayments.json"

export default {
    name: "UpcomingPaymentsDashboardComponent.vue",
    components: {UpcomingPaymentsItem},
    data: () => {
        return {
            bills: testPayments
        }
    }
}
</script>

<style scoped>

</style>