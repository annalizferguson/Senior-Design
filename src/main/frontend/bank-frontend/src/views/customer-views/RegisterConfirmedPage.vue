<template>
    <v-container style="height: calc(100vh - 200px)" class="d-flex justify-center align-center">
        <v-alert
            title="Registration Successful"
            text="Please log in with your new username and password."
            type="success"
        />
    </v-container>
</template>

<script>
import {useRouter} from "vue-router";
export default {
    name: "RegisterConfirmedPage.vue",
    data: () => {
        const router = useRouter()
        return {
            router: router
        }
    },
    mounted() {
        setTimeout(() => this.router.push('/customer-login'), 2000)
    }
}
</script>

<style scoped>

</style>