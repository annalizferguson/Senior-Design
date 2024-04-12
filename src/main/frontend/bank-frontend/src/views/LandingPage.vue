<template>
    <div
            class="d-flex justify-center align-center"
            style="height: calc(100vh - 100px)"
    >
        <v-card
                class="d-flex flex-wrap justify-center align-center"
                height="40%"
                width="70%"
                variant="flat"
        >
            <v-btn
                    width="100%"
                    height="35%"
                    color="#5ea4d6"
                    to="/customer-login"
            >
                I am a Customer
            </v-btn>
            <div>
                OR
            </div>
            <v-btn
                    width="100%"
                    height="35%"
                    color="#5ea4d6"
                    to="/teller-login"
            >
                I am an Employee
            </v-btn>
        </v-card>
    </div>
</template>

<script>
import {useCustomerStore} from "@/states/UserStore.js";
import {useTellerStore} from "@/states/TellerStore.js";
export default {
    name: "LandingPage.vue",
    data: () => {
        const customerStore = useCustomerStore()
        const tellerStore = useTellerStore()
        return {
            customerStore: customerStore,
            tellerStore: tellerStore
        }
    },
    beforeMount() {
        this.customerStore.$reset()
        this.tellerStore.$reset()
    }
}
</script>

<style scoped>

</style>
<script setup>
</script>