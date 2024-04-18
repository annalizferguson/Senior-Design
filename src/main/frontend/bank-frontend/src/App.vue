<template>
    <v-app>
        <v-app-bar
                class="d-flex"
                style="background-color: #1565c0; color: #ffffff"
        >
            <v-app-bar-title>
                <router-link to="/customer-dash" style="text-decoration: none; color: inherit;">Charger United Credit Union
                </router-link>
            </v-app-bar-title>
            <div class="mr-2">
                Routing Number: #859384192
            </div>
            <v-btn
                    to="/"
                    @click="clearStore"
            >
                Logout
            </v-btn>
        </v-app-bar>
        <v-main>
            <router-view/>
        </v-main>
    </v-app>
</template>

<script>
import {useCustomerStore} from "@/states/UserStore.js";
import {useTellerStore} from "@/states/TellerStore.js";
import {useAdminStore} from "@/states/AdminStore.js";

export default {
    data: function() {
        const store = useCustomerStore()
        const tellerStore = useTellerStore()
        const adminStore = useAdminStore()
        return {
            store: store,
            tellerStore: tellerStore,
            adminStore: adminStore
        }
    },
    methods: {
        clearStore: function() {
            this.store.resetStore()
            this.tellerStore.resetStore()
            this.adminStore.resetStore()
        }
    }


}
</script>
