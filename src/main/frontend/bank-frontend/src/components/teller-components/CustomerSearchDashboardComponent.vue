<template>
    <v-card
            width="100%"
            height="85%"
            variant="outlined"
    >
        <v-card-title>Customer Search</v-card-title>
        <v-text-field
                v-model="search"
                label="Search"
                prepend-inner-icon="mdi-magnify"
                variant="outlined"
                hide-details
                single-line
        ></v-text-field>
        <v-data-table
                :headers="header"
                :items="customers"
                :search="search"
                height="45vh"
        >
            <template v-slot:item.actions="{ item }">
                <v-btn
                        variant="outlined"
                        color="primary"
                        class="mr-2"
                >
                    View Details
                </v-btn>
                <v-btn
                        variant="outlined"
                        color="primary"
                        class="mr-2"
                >
                    Manage Accounts
                </v-btn>
                <v-btn
                        variant="outlined"m
                        color="primary"
                        class="mr-2"
                >
                    View Reports
                </v-btn>
                <v-btn
                        variant="outlined"
                        color="primary"
                        class="mr-2"
                >
                    View Alerts
                </v-btn>
                <v-btn
                        variant="outlined"
                        color="primary"
                        class="mr-2"
                >
                    View Bills
                </v-btn>
            </template>
        </v-data-table>
    </v-card>
</template>

<script>
import testCustomers from "@/test-files/testCustomers.json"

export default {
    name: "CustomerSearchDashboardComponent.vue",
    data: () => {
        return {
            customers: testCustomers,
            header: [
                {
                    title: 'Last Name',
                    align: 'start',
                    sortable: false,
                    key: 'lastname',
                },
                {title: 'First Name', key: 'firstname'},
                {title: 'Account #', key: 'accountNumber'},
                {title: 'Actions', key: 'actions', sortable: false},
            ],
            search: ""
        }
    }
}
</script>

<style scoped>

</style>