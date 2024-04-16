<template>
    <v-card
            width="100%"
            height="85%"
            variant="outlined"
    >
        <v-card-title>
            Teller Search
            <v-btn
                    class="ml-5"
                    color="#4097f5"
            >
                Create New Teller
            </v-btn>
        </v-card-title>
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
            :items="tellers"
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
                    View Reports
                </v-btn>
                <v-btn
                    variant="outlined"
                    color="primary"
                    class="mr-2"
                >
                    Delete Teller
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
    name: "TellerSearchDashboardComponent.vue",
    data: () => {
        const store = useTellerStore()
        const route = useRouter()
        return {
            store: store,
            route: route,
            tellers: [],
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
        async getTellers(){
            axios.get('/api/tellers').then((response) => {
                this.tellers = response.data
            })
        },
        goToDetails(id){
            this.store.setTeller(id)
            this.route.push('/teller-info')
        },
        goToAccounts(id){
          this.store.setTeller(id)
          this.route.push('/teller-accounts')
        }
    },
    beforeMount(){
        this.getTellers()
    }
}
</script>

<style scoped>

</style>

</style>
