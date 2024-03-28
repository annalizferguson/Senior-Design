<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 100px)">
        <v-card
                class="d-flex flex-wrap mb-1 overflow-y-auto"
                width="90%"
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
                    Your Dashboard
                    <div
                        style="font-size:0.85em"
                    >
                        Welcome, John Doe
                    </div>
                </v-card-title>
            </v-card>
            <OpenAccountsDashboardComponent/>
            <v-card
                    class="d-flex flex-wrap justify-center "
                    height="85%"
                    width="48%"
                    variant="flat"
            >
                <UpcomingPaymentsDashboardComponent/>
                <v-card
                        width="100%"
                        height="32%"
                        variant="outlined"
                        class="d-flex flex-wrap justify-center align-center mt-4"
                >
                    <div style="height: 90px; width: 250px; margin-right: 100px">
                        <v-btn
                                height="100%"
                                color="#4097f5"
                        >
                            Open New Account
                        </v-btn>
                    </div>
                    <div style="height: 90px; width: 250px">
                        <v-btn
                            height="100%"
                            color="#4097f5"
                        >
                            View Reports
                        </v-btn>
                    </div>
                    <div style="height: 90px; width: 250px; margin-right: 100px">
                        <v-btn
                            height="100%"
                            color="#4097f5"
                        >
                            View Account History
                        </v-btn>
                    </div>
                    <div style="height: 90px; width: 250px">
                        <v-btn
                            height="100%"
                            color="#4097f5"
                        >
                            Settings
                        </v-btn>
                    </div>
                </v-card>
            </v-card>
        </v-card>
    </div>
</template>

<script>
// Components
import OpenAccountsDashboardComponent from '../../components/customer-components/OpenAccountsDashboardComponent.vue';
import UpcomingPaymentsDashboardComponent from '../../components/customer-components/UpcomingPaymentsDashboardComponent.vue';
export default {
    name: "CustomerDashboard.vue",
    components: {OpenAccountsDashboardComponent, UpcomingPaymentsDashboardComponent}
}
</script>

<style scoped>

</style>