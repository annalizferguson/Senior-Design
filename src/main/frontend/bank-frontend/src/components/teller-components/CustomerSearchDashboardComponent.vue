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
                class="ml-5"
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
                        variant="outlined" m
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
                    View Bills
                </v-btn>
                <v-btn
                    variant="outlined"
                    color="primary"
                    class="mr-2"
                >
                    User Settings
                </v-btn>
            </template>
        </v-data-table>
    </v-card>
</template>

<script>
import axios from 'axios';

export default {
    name: "CustomerSearchDashboardComponent.vue",
    data: () => {
        return {
            customers: [],
            header: [
                {
                    title: 'ID',
                    align: 'start',
                    sortable: false,
                    key: 'id',
                },
                {title: 'Last Name', key: 'lastName'},
                {title: 'First Name', key: 'firstName'},
                {title: 'Actions', key: 'actions', sortable: false},
            ],
            search: ""
        }
    },
    methods: {
        async getCustomers() {
            axios.get('/api/customers').then((response) => {
                this.customers = response.data
            })
        }
    },
    beforeMount() {
        this.getCustomers()
    }
}
</script>

<style scoped>

</style>