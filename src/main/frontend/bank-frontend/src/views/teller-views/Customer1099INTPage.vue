<template>
    <v-container>
        <v-card height="10%" class="mb-4">
            <v-card-title class="d-flex justify-space-between">
                Retrieve 1099-INT Form for {{ firstName }} {{ lastName }}
                <v-btn
                        variant="plain"
                        color="primary"
                        to="/teller-dash"
                >
                    Return to Dashboard
                </v-btn>
            </v-card-title>
            <v-container>
                Select a year:
                <v-select
                        v-model="chosenYear"
                        :items="years"
                />
            </v-container>
        </v-card>
    </v-container>
    <v-container v-if="chosenYear">
        <INTForm :year="chosenYear" :key="chosenYear"/>
    </v-container>
</template>

<script>
import INTForm from "@/components/customer-components/INTForm.vue";
import {useTellerStore} from "@/states/TellerStore.js";

export default {
    name: "Customer1099INTPage.vue",
    components: {INTForm},
    data: () => {
        const store = useTellerStore()
        return {
            years: ["2020", "2021", "2022", "2023", "2024"],
            chosenYear: "",
            firstName: store.getCustomerFirstName,
            lastName: store.getCustomerLastName
        }
    }
}
</script>

<style scoped>

</style>