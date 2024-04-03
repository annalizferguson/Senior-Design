<template>
    <v-app>
        <v-app-bar
                class="d-flex"
                style="background-color: #1565c0; color: #ffffff"
        >
            <v-app-bar-title>
                <router-link to="/" style="text-decoration: none; color: inherit;">Charger United Credit Union
                </router-link>
            </v-app-bar-title>
            <div class="mr-2">
                Routing Number: #123456789
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
export default {
    data: function() {
        const store = useCustomerStore()
        return {
            store: store
        }
    },
    methods: {
        clearStore: function() {
            this.store.$reset()
        }
    }


}
</script>
