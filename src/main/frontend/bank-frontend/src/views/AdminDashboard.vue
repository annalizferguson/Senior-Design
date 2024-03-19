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
                    Administrator Dashboard
                    <div>
                        <v-btn
                                variant="outlined"
                                class="mr-4"
                                @click="changeFocus()"
                        >
                            View {{oppositeFocus}}
                        </v-btn>
                        <v-btn
                            variant="outlined"
                        >
                            Settings
                        </v-btn>
                    </div>
                </v-card-title>
            </v-card>
            <TellerSearchDashboardComponent v-if="isTellerFocus"/>
            <CustomerSearchDashboardComponent v-if="!isTellerFocus" width="100%"/>
        </v-card>
    </div>
</template>

<script>
import TellerSearchDashboardComponent from "@/components/admin-components/TellerSearchDashboardComponent.vue";
import CustomerSearchDashboardComponent from "@/components/teller-components/CustomerSearchDashboardComponent.vue";
export default {
    name: "AdminDashboard.vue",
    components: {TellerSearchDashboardComponent, CustomerSearchDashboardComponent},

    data: () => {
        return {
            oppositeFocus: "Customers",
            isTellerFocus: true,
        }
    },

    methods: {
        changeFocus: function() {
            this.isTellerFocus = !this.isTellerFocus
            this.oppositeFocus = this.isTellerFocus? "Customers":"Tellers"
        },
    }
}
</script>

<style scoped>

</style>