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
                    <div>
                        Your Dashboard
                        <v-btn
                                color="white"
                                variant="plain"
                                to="/1099-INT"
                        >
                            1099-INT Form
                        </v-btn>
                        <v-btn
                                color="white"
                                variant="plain"
                                to="/customer-settings"
                        >
                            Settings
                        </v-btn>
                    </div>
                    <div
                            style="font-size:0.85em"
                    >
                        Welcome, {{ firstname }} {{ lastname }}
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
            </v-card>
        </v-card>
    </div>
</template>

<script>
// Components
import OpenAccountsDashboardComponent from '../../components/customer-components/OpenAccountsDashboardComponent.vue';
import UpcomingPaymentsDashboardComponent
    from '../../components/customer-components/UpcomingPaymentsDashboardComponent.vue';
import {useCustomerStore} from "@/states/UserStore.js";
import {useRouter} from "vue-router";

export default {
    name: "CustomerDashboard.vue",
    components: {OpenAccountsDashboardComponent, UpcomingPaymentsDashboardComponent},
    data: () => {
        const store = useCustomerStore()
        const router = useRouter()
        return {
            store: store,
            router: router,
            customer_id: store.getID,
            firstname: store.getFirstName,
            lastname: store.getLastName
        }
    },
}
</script>

<style scoped>

</style>