<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 100px)">
        <v-card
                class="d-flex flex-wrap mb-1"
                width="1250"
                style="height:calc(100vh - 200px)"
                variant="flat"
        >
            <v-card
                    width="100%"
                    height="10%"
                    variant="elevated"
                    color="#4097f5"
            >
                <v-card-title
                        class="d-flex justify-space-between"
                >
                    Teller Dashboard
                    <div>
                        <v-btn
                                variant="outlined"
                                class="mr-4"
                                to="/create-customer"
                        >
                            Create New Customer
                        </v-btn>
                    </div>
                </v-card-title>
            </v-card>
            <CustomerSearchDashboardComponent/>
        </v-card>
    </div>
</template>

<script>

import CustomerSearchDashboardComponent from "@/components/teller-components/CustomerSearchDashboardComponent.vue";
import AlertsDashboardComponent from "@/components/teller-components/AlertsDashboardComponent.vue";

export default {
    name: "TellerDashboard.vue",
    components: {CustomerSearchDashboardComponent, AlertsDashboardComponent}
}
</script>

<style scoped>

</style>