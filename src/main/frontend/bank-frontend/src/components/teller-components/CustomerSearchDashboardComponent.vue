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
                        @click="goToDetails(item)"
                >
                    View Details
                </v-btn>
                <v-btn
                        variant="outlined"
                        color="primary"
                        class="mr-2"
                        @click="goToAccounts(item)"
                >
                    Manage Accounts
                </v-btn>
                <v-btn
                        variant="outlined"
                        color="primary"
                        class="mr-2"
                        @click="goToReports(item)"
                >
                    View Reports
                </v-btn>
                <v-btn
                        variant="outlined"
                        color="primary"
                        class="mr-2"
                        @click="goToBills(item)"
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
import {useTellerStore} from "@/states/TellerStore.js";
import {useRouter} from "vue-router";

export default {
    name: "CustomerSearchDashboardComponent.vue",
    data: () => {
        const store = useTellerStore()
        const route = useRouter()
        return {
            store: store,
            route: route,
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
        },
        goToDetails(item) {
            this.store.setCustomer(item.id, item.firstName, item.lastName)
            this.route.push('/customer-info')
        },
        goToAccounts(item) {
            this.store.setCustomer(item.id, item.firstName, item.lastName)
            this.route.push('/customer-accounts')
        },
        goToBills(item) {
            this.store.setCustomer(item.id, item.firstName, item.lastName)
            this.route.push('/customer-bills')
        },
        goToReports(item) {
            this.store.setCustomer(item.id, item.firstName, item.lastName)
            this.route.push('/customer-report')
        }
    },
    beforeMount() {
        this.getCustomers()
    }
}
</script>

<style scoped>

</style>